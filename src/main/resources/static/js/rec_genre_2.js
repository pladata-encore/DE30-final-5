document.addEventListener("DOMContentLoaded", function() {
    const urlParams = new URLSearchParams(window.location.search);
    const genre = urlParams.get('genre');
    const itemsPerPage = 30; // 5줄 * 6개/줄 = 30개
    let currentPage = 1;
    let movies = [];

    // 영화 데이터 가져오기
    function fetchMovies() {
        const url = genre ? `/genremovies?genre=${encodeURIComponent(genre)}` : '/genremovies';

        fetch(url)
            .then(response => response.json())
            .then(data => {
                movies = data;
                displayMovies();
                setupPagination();
            })
            .catch(error => console.error('Error fetching movie data:', error));
    }

    // 영화 목록 표시
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

    // 페이지 네비게이션 설정
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

    // 장르 정보 업데이트 및 영화 데이터 가져오기
    function updateGenreAndMovies(genre) {
        document.querySelector('.selected-genre').textContent = genre || '선택된 장르';
        fetchMovies();
    }

    // 장르가 URL에 있을 때 처리
    if (genre) {
        updateGenreAndMovies(genre);
    } else {
        console.error('Genre parameter is missing in the URL.');
    }

    // nav-item 클릭 이벤트 처리
    const navItems = document.querySelectorAll('.nav-item');
    navItems.forEach((item, index) => {
        item.addEventListener('click', function() {
            if (index === 0) {
                window.location.href = '/rec_intro'; // 홈 버튼 클릭 시 이동
            } else if (index === 1) {
                window.location.href = '/rec_genre_1'; // 장르 선택 페이지로 이동
            } else if (index === 2) {
                window.location.href = '/rec_weather_1'; // 날씨별 영화 페이지로 이동
            }
        });
    });
});
