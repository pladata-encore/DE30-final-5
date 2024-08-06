document.addEventListener("DOMContentLoaded", function() {
    const urlParams = new URLSearchParams(window.location.search);
    const weatherCondition = urlParams.get('weather');
    const itemsPerPage = 5 * 6; // 5줄 * 6개/줄 = 30개
    let currentPage = 1;
    let movies = [];

    function fetchMovies() {
        const url = weatherCondition ? `/weathermovies?weather=${weatherCondition}` : `/weathermovies`;

        fetch(url)
            .then(response => response.json())
            .then(data => {
                movies = data;
                displayMovies();
                setupPagination();
            })
            .catch(error => console.error('Error fetching movie data:', error));
    }

    function displayMovies() {
        const movieList = document.getElementById('movie-list');
        movieList.innerHTML = '';

        const start = (currentPage - 1) * itemsPerPage;
        const end = Math.min(start + itemsPerPage, movies.length);
        const currentMovies = movies.slice(start, end);

        currentMovies.forEach(movie => {
            const movieItem = document.createElement('div');
            movieItem.className = 'movie-item';
            movieItem.innerHTML = `<img src="${movie.posterUrl}" alt="${movie.title}">`;
            movieList.appendChild(movieItem);
        });
    }

    function setupPagination() {
        const totalPages = Math.ceil(movies.length / itemsPerPage);
        const pagination = document.getElementById('pagination');
        pagination.innerHTML = '';

        // Previous button
        const prevButton = document.createElement('button');
        prevButton.textContent = 'Previous';
        prevButton.disabled = currentPage === 1;
        prevButton.addEventListener('click', () => {
            if (currentPage > 1) {
                currentPage--;
                displayMovies();
                setupPagination();
            }
        });
        pagination.appendChild(prevButton);

        // Page numbers
        const pageRange = 5; // Number of page buttons to show at once
        const startPage = Math.max(1, currentPage - Math.floor(pageRange / 2));
        const endPage = Math.min(totalPages, startPage + pageRange - 1);

        for (let i = startPage; i <= endPage; i++) {
            const pageButton = document.createElement('button');
            pageButton.textContent = i;
            pageButton.disabled = i === currentPage;
            pageButton.addEventListener('click', () => {
                currentPage = i;
                displayMovies();
                setupPagination();
            });
            pagination.appendChild(pageButton);
        }

        // Next button
        const nextButton = document.createElement('button');
        nextButton.textContent = 'Next';
        nextButton.disabled = currentPage === totalPages;
        nextButton.addEventListener('click', () => {
            if (currentPage < totalPages) {
                currentPage++;
                displayMovies();
                setupPagination();
            }
        });
        pagination.appendChild(nextButton);

        // Page info
        const pageInfo = document.createElement('span');
        pageInfo.textContent = `Page ${currentPage} of ${totalPages}`;
        pagination.appendChild(pageInfo);
    }

    function getWeatherIconCode(condition) {
        const iconMapping = {
            thunderstorm: '11d',
            drizzle: '09d',
            rain: '10d',
            snow: '13d',
            clear: '01d',
            clouds: '03d',
            fog: '50d',
            dust: '50n',
            typhoon: '11n',
            windy: '50n',
        };
        return iconMapping[condition] || '01d';
    }

    if (weatherCondition) {
        const iconCode = getWeatherIconCode(weatherCondition);
        const weatherIconUrl = `http://openweathermap.org/img/wn/${iconCode}.png`;

        document.querySelector('.weather-condition').innerHTML = `
            ${weatherCondition}
            <img src="${weatherIconUrl}" alt="Weather Icon" class="weather-icon">
        `;
        fetchMovies();
    } else if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(function(position) {
            const lat = position.coords.latitude;
            const lon = position.coords.longitude;

            fetch(`/weather?lat=${lat}&lon=${lon}`)
                .then(response => response.json())
                .then(data => {
                    if (data && data.weather && data.weather.length > 0) {
                        const weatherCondition = data.weather[0].main.toLowerCase();
                        const iconCode = data.weather[0].icon;
                        updateWeatherAndMovies(weatherCondition, iconCode);
                    } else {
                        console.error('Weather data is missing');
                    }
                })
                .catch(error => console.error('Error fetching weather data:', error));
        }, function(error) {
            console.error('Error getting location:', error);
        });
    } else {
        console.error('Geolocation is not supported by this browser.');
    }

    function updateWeatherAndMovies(weatherCondition, iconCode) {
        const weatherIconUrl = `http://openweathermap.org/img/wn/${iconCode}.png`;

        document.querySelector('.weather-condition').innerHTML = `
            ${weatherCondition}
            <img src="${weatherIconUrl}" alt="Weather Icon" class="weather-icon">
        `;

        fetchMovies();
    }
});
