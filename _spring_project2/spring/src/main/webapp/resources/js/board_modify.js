async function removeFileToServer(uuid) {
    try {
        const url = '/board/file/' + uuid;
        const config = {
            method : 'delete'
        }
        const resp = await fetch(url, config);
        const result = await resp.text();
        return result;
    } catch (error) {
        console.log(error);
    }    
}

document.addEventListener('click', e => {
    if (e.target.classList.contains('file-x')) {
        let uuid = e.target.dataset.uuid;
        console.log(uuid);

        removeFileToServer(uuid).then(result => {
            console.log(result);

            alert('파일 삭제 : ' + (parseInt(result) > 0 ? '성공' : '실패'));

            if (result > 0) {
                e.target.closest('li').remove();
            }
        });
    }
});