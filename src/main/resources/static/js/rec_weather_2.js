document.addEventListener("DOMContentLoaded", function() {
    if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(function(position) {
            const lat = position.coords.latitude;
            const lon = position.coords.longitude;

            fetch(`/weather?lat=${lat}&lon=${lon}`)
                .then(response => response.json())
                .then(data => {
                    if (data && data.weather && data.weather.length > 0) {
                        const weatherCondition = data.weather[0].main.toLowerCase(); // 날씨 상태 (예: rain, clear)
                        const iconCode = data.weather[0].icon;
                        const weatherIconUrl = `http://openweathermap.org/img/wn/${iconCode}.png`;

                        // 날씨 상태와 아이콘을 문장에 삽입
                        document.querySelector('.weather-condition').innerHTML = `${weatherCondition}<img src="${weatherIconUrl}" alt="Weather Icon" class="weather-icon">`;

                        // 해당 날씨에 맞는 영화 추천 목록을 가져옴
                        fetch(`/weathermovies?weather=${weatherCondition}`)
                            .then(response => response.json())
                            .then(movies => {
                                const itemsPerPage = 5 * 6; // 5줄 * 6개/줄 = 30개
                                let currentPage = 1;

                                function displayMovies() {
                                    const movieList = document.getElementById('movie-list');
                                    movieList.innerHTML = '';

                                    const start = (currentPage - 1) * itemsPerPage;
                                    const end = Math.min(start + itemsPerPage, movies.length);
                                    const currentMovies = movies.slice(start, end);

                                    currentMovies.forEach(movie => {
                                        const movieItem = document.createElement('div');
                                        movieItem.className = 'movie-item';
                                        movieItem.innerHTML = `
                                            <a href="/movie_detail.html?id=${movie.id}">
                                                <img src="${movie.posterUrl}" alt="${movie.title}">
                                                <p>${movie.title}</p>
                                            </a>
                                        `;
                                        movieList.appendChild(movieItem);
                                    });
                                }

                                function setupPagination() {
                                    const totalPages = Math.ceil(movies.length / itemsPerPage);
                                    const pagination = document.getElementById('pagination');
                                    pagination.innerHTML = '';

                                    // 이전 버튼
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

                                    // 페이지 번호 버튼
                                    const pageRange = 5; // 페이지 버튼 개수
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

                                    // 다음 버튼
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

                                    // 페이지 정보 표시
                                    const pageInfo = document.createElement('span');
                                    pageInfo.textContent = `Page ${currentPage} of ${totalPages}`;
                                    pagination.appendChild(pageInfo);
                                }

                                displayMovies();
                                setupPagination();
                            })
                            .catch(error => console.error('Error fetching movie data:', error));
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
});
