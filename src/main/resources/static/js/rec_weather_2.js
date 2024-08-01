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
                                const movieList = document.getElementById('movie-list');
                                movieList.innerHTML = ''; // 기존 목록 초기화
                                movies.forEach(movie => {
                                    const movieItem = document.createElement('div');
                                    movieItem.innerHTML = `<img src="${movie.posterUrl}" alt="${movie.title}">`;
                                    movieList.appendChild(movieItem);
                                });
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
