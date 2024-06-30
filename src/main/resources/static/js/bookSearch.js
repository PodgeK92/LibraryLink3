document.addEventListener('DOMContentLoaded', function () {
    const resultsContainer = document.querySelector('#search-results');
    const params = new URLSearchParams(window.location.search);
    const page = parseInt(params.get('page')) || 1;

    const fetchBooks = () => {
        let queryString = 'https://www.googleapis.com/books/v1/volumes?q=';
        queryString += params.get('bookName') ? `intitle:${encodeURIComponent(params.get('bookName'))}` : '';
        queryString += params.get('author') ? `+inauthor:${encodeURIComponent(params.get('author'))}` : '';
        queryString += params.get('isbn') ? `+isbn:${encodeURIComponent(params.get('isbn'))}` : '';
        queryString += params.get('publisher') ? `+inpublisher:${encodeURIComponent(params.get('publisher'))}` : '';
        queryString += params.get('format') ? `+subject:${encodeURIComponent(params.get('format'))}` : '';
        queryString += params.get('genre') ? `+subject:${encodeURIComponent(params.get('genre'))}` : '';
        queryString += params.get('yearPublished') ? `+inpublisherDate:${encodeURIComponent(params.get('yearPublished'))}` : '';
        queryString += `&startIndex=${(page - 1) * 10}&maxResults=10`;

        fetch(queryString)
            .then(response => response.json())
            .then(data => {
            resultsContainer.innerHTML = '';
            if (!data.items) {
                resultsContainer.innerHTML = '<div class="list-group-item">No results found.</div>';
                return;
            }
            data.items.forEach((item, index) => {
                const volumeInfo = item.volumeInfo;
                const title = volumeInfo.title;
                const authors = volumeInfo.authors ? volumeInfo.authors.join(', ') : 'Unknown author';
                const description = volumeInfo.description || 'No description available.';
                const coverUrl = volumeInfo.imageLinks ? volumeInfo.imageLinks.thumbnail : 'no-cover-image.jpg';
                const isbns = volumeInfo.industryIdentifiers ? volumeInfo.industryIdentifiers.map(id => id.identifier).join(', ') : 'ISBN not available';

                const sanitizedIsbn = isbns.replace(/[^a-zA-Z0-9]/g, '');
                const bookItemHTML = `
                        <a href="#" class="list-group-item list-group-item-action flex-column align-items-start">
                          <div class="d-flex w-100 justify-content-between">
                            <h5 class="mb-1">${title}</h5>
                            <small class="availability" id="isbn-${sanitizedIsbn}">Availability: Unavailable</small>
                          </div>
                          <p class="mb-1">Author: ${authors}</p>
                          <p class="mb-1">Genre: ${volumeInfo.categories ? volumeInfo.categories.join(', ') : 'Genre not available'}</p>
                          <p class="mb-1">Year Published: ${volumeInfo.publishedDate || 'Year not available'}</p>
                          <small>ISBN: ${isbns}</small>
                          <img src="${coverUrl}" alt="Cover image" class="img-fluid">
                          <div class="collapse mt-3" id="collapseDescription${index}">
                            <p>Description: ${description}</p>
                          </div>
                          <a class="btn btn-sm btn-outline-primary read-more-btn" data-toggle="collapse" href="#collapseDescription${index}" role="button" aria-expanded="false" aria-controls="collapseDescription${index}">Read More</a>
                        </a>
                      `;
                resultsContainer.innerHTML += bookItemHTML;

                // Check availability for each ISBN
                isbns.split(', ').forEach(isbn => checkBookAvailability(isbn));
            });

            // Add pagination controls
            addPaginationControls(data.totalItems, page);
        })
            .catch(error => {
            console.error('Error fetching data:', error);
            resultsContainer.innerHTML = '<div class="list-group-item">Failed to retrieve data. Please check your network connection and try again.</div>';
        });
    };

    const checkBookAvailability = (isbn) => {
        fetch(`/api/books/checkAvailability?isbn=${isbn}`)
            .then(response => response.json())
            .then(isAvailable => {
            const availabilityText = isAvailable ? 'Available' : 'Unavailable';
            const availabilityElement = document.querySelector(`#isbn-${isbn.replace(/[^a-zA-Z0-9]/g, '')}`);
            if (availabilityElement) {
                availabilityElement.textContent = `Availability: ${availabilityText}`;
            }
        })
            .catch(error => console.error('Error checking availability:', error));
    };

    const addPaginationControls = (totalItems, currentPage) => {
        const paginationContainer = document.querySelector('#pagination-controls .pagination');
        const itemsPerPage = 10;
        const totalPages = Math.ceil(totalItems / itemsPerPage);
        const maxPagesToShow = 5; // Limit the number of pages to show at once

        paginationContainer.innerHTML = '';

        const createPageItem = (page, text = page) => {
            const li = document.createElement('li');
            li.classList.add('page-item');
            if (page === currentPage) li.classList.add('active');

            const a = document.createElement('a');
            a.classList.add('page-link');
            a.href = `?page=${page}`;
            a.textContent = text;

            li.appendChild(a);
            return li;
        };

        if (currentPage > 1) {
            paginationContainer.appendChild(createPageItem(currentPage - 1, '«'));
        }

        const startPage = Math.max(1, currentPage - Math.floor(maxPagesToShow / 2));
        const endPage = Math.min(totalPages, startPage + maxPagesToShow - 1);

        for (let i = startPage; i <= endPage; i++) {
            paginationContainer.appendChild(createPageItem(i));
        }

        if (currentPage < totalPages) {
            paginationContainer.appendChild(createPageItem(currentPage + 1, '»'));
        }
    };

    fetchBooks();
});

