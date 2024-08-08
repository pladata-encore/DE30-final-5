document.addEventListener("DOMContentLoaded", function() {
    const urlParams = new URLSearchParams(window.location.search);
    const movieId = urlParams.get('id');

    if (movieId) {
        fetch(`/api/movie_detail/${movieId}`)
            .then(response => {
                if (!response.ok) {
                    throw new Error('영화를 찾을 수 없습니다');
                }
                return response.json();
            })
            .then(movie => {
                // 영화 정보 업데이트
                document.getElementById('movie-title-header').textContent = movie.title;
                document.getElementById('movie-poster').src = movie.posterUrl;
                document.getElementById('movie-original-language').textContent = movie.originalLanguage;
                document.getElementById('movie-genre').textContent = movie.genre;
                document.getElementById('movie-runtime').textContent = movie.runtime + " 분";
                document.getElementById('movie-rating').textContent = movie.rating;
                document.getElementById('movie-release-date').textContent = movie.releaseDate;
                document.getElementById('movie-synopsis').textContent = movie.synopsis;
                document.getElementById('movie-cast').textContent = movie.cast;
                document.getElementById('movie-director').textContent = movie.director;
                document.getElementById('movie-series').textContent = movie.series;

                // YouTube 비디오 iFrame 생성
                const trailerUrl = movie.trailer;
                console.log('Trailer URL:', trailerUrl); // URL 확인

                if (trailerUrl) {
                    // YouTube URL이 맞는지 확인
                    const embedUrl = trailerUrl.replace('watch?v=', 'embed/');
                    console.log('Embed URL:', embedUrl); // 변환된 URL 확인

                    const iframe = document.createElement('iframe');
                    iframe.src = `${embedUrl}?autoplay=1&mute=1`; // 자동 재생 및 음소거 추가
                    iframe.frameBorder = "0";
                    iframe.allow = "accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture";
                    iframe.allowFullscreen = true;
                    iframe.width = "100%";
                    iframe.height = "100%";

                    const trailerContainer = document.getElementById('movie-trailer-container');
                    trailerContainer.innerHTML = ''; // 기존 내용을 지우고 새 iFrame 추가
                    trailerContainer.appendChild(iframe);

                    // 음소거 버튼 이벤트 리스너 추가
                    const muteButton = document.getElementById('mute-button');
                    let isMuted = true;

                    muteButton.addEventListener('click', function() {
                        const iframeElement = trailerContainer.querySelector('iframe');
                        if (iframeElement) {
                            const iframeSrc = iframeElement.src;
                            if (isMuted) {
                                iframeElement.src = iframeSrc.replace('mute=1', 'mute=0');
                                muteButton.textContent = '음소거 해제';
                            } else {
                                iframeElement.src = iframeSrc.replace('mute=0', 'mute=1');
                                muteButton.textContent = '음소거';
                            }
                            isMuted = !isMuted;
                        }
                    });
                } else {
                    console.error('YouTube 비디오 URL을 찾을 수 없습니다.');
                }

                // 내용 애니메이션 시작
                setTimeout(() => {
                    document.querySelector('.movie-info').classList.add('show');
                }, 1000); // 1초 후 내용이 나타나도록 설정
            })
            .catch(error => console.error('영화 데이터를 가져오는 중 오류 발생:', error));
    } else {
        console.error('URL에 영화 ID가 지정되지 않았습니다.');
    }
});
