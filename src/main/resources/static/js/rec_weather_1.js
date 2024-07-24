document.addEventListener("DOMContentLoaded", function() {
    if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(function(position) {
            const lat = position.coords.latitude;
            const lon = position.coords.longitude;

            fetch(`/weather?lat=${lat}&lon=${lon}`)
                .then(response => response.json())
                .then(data => {
                    console.log('Weather data:', data); // 데이터 로그 출력
                    console.log('Weather icon URL:', weatherIconUrl);
                    if (data && data.weather && data.weather.length > 0) {
                        const iconCode = data.weather[0].icon;
                        const weatherIconUrl = `http://openweathermap.org/img/wn/${iconCode}.png`;
                        document.getElementById('weather-icon').src = weatherIconUrl;
                        document.getElementById('weather-info').style.display = 'flex'; // Ensure weather info is visible
                    } else {
                        console.error('Weather icon data is missing');
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
