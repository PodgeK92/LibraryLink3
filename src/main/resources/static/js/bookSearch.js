document.addEventListener('DOMContentLoaded', function () {
    const resultsContainer = document.querySelector('#search-results');
    const paginationControls = document.querySelector('#pagination-controls .pagination');
    const params = new URLSearchParams(window.location.search);

    const fetchBooks = () => {
        let queryString = 'https://www.googleapis.com/books/v1/volumes?q=';
        queryString += params.get('bookName') ? `intitle:${encodeURIComponent(params.get('bookName'))}` : '';
        queryString += params.get('author') ? `+inauthor:${encodeURIComponent(params.get('author'))}` : '';
        queryString += params.get('isbn') ? `+isbn:${encodeURIComponent(params.get('isbn'))}` : '';
        queryString += params.get('publisher') ? `+inpublisher:${encodeURIComponent(params.get('publisher'))}` : '';
        queryString += params.get('format') ? `+subject:${encodeURIComponent(params.get('format'))}` : '';
        queryString += params.get('genre') ? `+subject:${encodeURIComponent(params.get('genre'))}` : '';
        queryString += params.get('yearPublished') ? `+inpublisherDate:${encodeURIComponent(params.get('yearPublished'))}` : '';

        const page = parseInt(params.get('page')) || 1;
        const maxResults = 10;
        queryString += `&startIndex=${(page - 1) * maxResults}&maxResults=${maxResults}`;

        fetch(queryString)
            .then(response => response.json())
            .then(data => {
            resultsContainer.innerHTML = '';
            if (!data.items) {
                resultsContainer.innerHTML = '<div class="list-group-item">No results found.</div>';
                return;
            }

            let books = data.items.map((item, index) => {
                const volumeInfo = item.volumeInfo;
                const title = volumeInfo.title;
                const authors = volumeInfo.authors ? volumeInfo.authors.join(', ') : 'Unknown author';
                const description = volumeInfo.description || 'No description available.';
                const coverUrl = volumeInfo.imageLinks ? volumeInfo.imageLinks.thumbnail : 'no-cover-image.jpg';
                const isbns = volumeInfo.industryIdentifiers ? volumeInfo.industryIdentifiers.map(id => id.identifier).join(', ') : 'ISBN not available';

                // Use only the first ISBN for generating the ID to avoid duplication issues
                const primaryIsbn = volumeInfo.industryIdentifiers ? volumeInfo.industryIdentifiers[0].identifier.replace(/[^a-zA-Z0-9]/g, '') : 'ISBNnotavailable';

                return {
                    title,
                    authors,
                    description,
                    coverUrl,
                    isbns,
                    primaryIsbn,
                    index,
                    availability: 'Unavailable' // Default availability
                };
            });

            // Check availability for each book and sort them
            let availabilityPromises = books.map(book => {
                return checkBookAvailability(book.isbns.split(', '), book.primaryIsbn).then(isAvailable => {
                    book.availability = isAvailable ? 'Available' : 'Unavailable';
                    return book;
                });
            });

            Promise.all(availabilityPromises).then(sortedBooks => {
                sortedBooks.sort((a, b) => (a.availability === 'Available' ? -1 : 1));
                sortedBooks.forEach(book => {
                    const bookItemHTML = `
                            <a href="#" class="list-group-item list-group-item-action flex-column align-items-start">
                                <div class="d-flex w-100 justify-content-between">
                                    <h5 class="mb-1">${book.title}</h5>
                                    <small class="availability" id="isbn-${book.primaryIsbn}">Availability: ${book.availability}</small>
                                </div>
                                <p class="mb-1">Author: ${book.authors}</p>
                                <p class="mb-1">Genre: ${book.categories ? book.categories.join(', ') : 'Genre not available'}</p>
                                <p class="mb-1">Year Published: ${book.publishedDate || 'Year not available'}</p>
                                <small>ISBN: ${book.isbns}</small>
                                <img src="${book.coverUrl}" alt="Cover image" class="img-fluid">
                                <div class="collapse mt-3" id="collapseDescription${book.index}">
                                    <p>Description: ${book.description}</p>
                                </div>
                                <a class="btn btn-sm btn-outline-primary" data-toggle="collapse" href="#collapseDescription${book.index}" role="button" aria-expanded="false" aria-controls="collapseDescription${book.index}">Read More</a>
                            </a>
                        `;
                    resultsContainer.innerHTML += bookItemHTML;
                });

                // Add pagination controls
                addPaginationControls(data.totalItems, page);
            });
        })
            .catch(error => {
            console.error('Error fetching data:', error);
            resultsContainer.innerHTML = '<div class="list-group-item">Failed to retrieve data. Please check your network connection and try again.</div>';
        });
    };

    const checkBookAvailability = (isbns, primaryIsbn) => {
        let availabilityChecks = isbns.map(isbn => {
            const sanitizedIsbn = isbn.replace(/[^a-zA-Z0-9]/g, '');
            return fetch(`/api/googlebooks/checkAvailability?isbn=${sanitizedIsbn}`)
                .then(response => response.json())
                .then(isAvailable => isAvailable)
                .catch(error => {
                console.error('Error checking availability:', error);
                return false;
            });
        });

        return Promise.all(availabilityChecks).then(results => results.includes(true));
    };

    const addPaginationControls = (totalItems, currentPage) => {
        const paginationContainer = document.querySelector('#pagination-controls .pagination');
        const itemsPerPage = 10;
        const totalPages = Math.ceil(totalItems / itemsPerPage);
        const maxPagesToShow = 5;

        paginationContainer.innerHTML = '';

        const createPageItem = (page, text = page) => {
            const li = document.createElement('li');
            li.classList.add('page-item');
            if (page === currentPage) li.classList.add('active');

            const a = document.createElement('a');
            a.classList.add('page-link');

            const params = new URLSearchParams(window.location.search);
            params.set('page', page);
            a.href = `?${params.toString()}`;
            a.textContent = text;

            li.appendChild(a);
            return li;
        };

        // First page button
        if (currentPage > 1) {
            paginationContainer.appendChild(createPageItem(1, '«'));
        }

        // Single backward button
        if (currentPage > 1) {
            paginationContainer.appendChild(createPageItem(currentPage - 1, '‹'));
        }

        const startPage = Math.max(1, currentPage - Math.floor(maxPagesToShow / 2));
        const endPage = Math.min(totalPages, startPage + maxPagesToShow - 1);

        for (let i = startPage; i <= endPage; i++) {
            paginationContainer.appendChild(createPageItem(i));
        }

        // Single forward button
        if (currentPage < totalPages) {
            paginationContainer.appendChild(createPageItem(currentPage + 1, '›'));
        }
    };

    fetchBooks();
});
