document.addEventListener("DOMContentLoaded", function() {
    // URL에서 날씨 파라미터 추출
    const urlParams = new URLSearchParams(window.location.search);
    const weatherCondition = urlParams.get('weather');

    if (weatherCondition) {
        // 날씨 상태를 문장에 삽입
        const iconCode = getWeatherIconCode(weatherCondition);
        const weatherIconUrl = `http://openweathermap.org/img/wn/${iconCode}.png`;

        document.querySelector('.weather-condition').innerHTML = `
            ${weatherCondition}
            <img src="${weatherIconUrl}" alt="Weather Icon" class="weather-icon">
        `;

        // 해당 날씨에 맞는 영화 추천 목록을 가져옴
        fetch(`/weathermovies?weather=${weatherCondition}`)
            .then(response => response.json())
            .then(movies => {
                const movieList = document.getElementById('movie-list');
                movieList.innerHTML = ''; // 기존 목록 초기화
                if (movies.length > 0) {
                    movies.forEach(movie => {
                        const movieItem = document.createElement('div');
                        movieItem.className = 'movie-item';
                        movieItem.innerHTML = `<img src="${movie.posterUrl}" alt="${movie.title}">`;
                        movieList.appendChild(movieItem);
                    });
                } else {
                    movieList.innerHTML = '<p>No movies found for the current weather.</p>';
                }
            })
            .catch(error => console.error('Error fetching movie data:', error));
    } else if (navigator.geolocation) {
        // 위치 기반으로 날씨를 가져오는 경우
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

        // 해당 날씨에 맞는 영화 추천 목록을 가져옴
        fetch(`/weathermovies?weather=${weatherCondition}`)
            .then(response => response.json())
            .then(movies => {
                const movieList = document.getElementById('movie-list');
                movieList.innerHTML = ''; // 기존 목록 초기화
                if (movies.length > 0) {
                    movies.forEach(movie => {
                        const movieItem = document.createElement('div');
                        movieItem.className = 'movie-item';
                        movieItem.innerHTML = `<img src="${movie.posterUrl}" alt="${movie.title}">`;
                        movieList.appendChild(movieItem);
                    });
                } else {
                    movieList.innerHTML = '<p>No movies found for the current weather.</p>';
                }
            })
            .catch(error => console.error('Error fetching movie data:', error));
    }

    function getWeatherIconCode(condition) {
        // 날씨 상태에 맞는 아이콘 코드 반환
        const iconMapping = {
            thunderstorm: '11d', // Thunderstorm (낮)
            drizzle: '09d', // Drizzle (낮)
            rain: '10d', // Rain (낮)
            snow: '13d', // Snow (낮)
            clear: '01d', // Clear (낮)
            clouds: '03d', // Clouds (낮)
            windy: '50n', // Windy (대부분의 API에서 제공하지 않지만, 대체로 '50d' 사용 가능)
            fog: '50d',             // 안개 (낮)
            dust: '50n', // Dust (오염과 유사하게 처리)
            typhoon: '11n', // Typhoon (대부분의 API에서는 '11d'로 처리)
        };
        return iconMapping[condition] || '01d'; // 기본 아이콘으로 '01d' 반환
    }
});
