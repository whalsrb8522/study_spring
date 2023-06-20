document.getElementById('trigger').addEventListener('click', () => {
    document.getElementById('file').click();
});

// 정규표현식을 사용하여 생성자 함수를 만들기
// 실행파일 막기, 이미지 파일인지 아닌지 체크
// fileUpload시 형식제한 함수
const regExp = new RegExp("\.(exe|sh|bat|msi|dll|js)$");        // 실행파일 막기
const regExpImg = new RegExp("\.(jsp|jpeg|png|gif|bmp)$");      // 이미지 파일
const maxSize = 20 * 1024 * 1024;       // 20mb

function fileSizeValidation(fileName, fileSize) {
    if (regExp.test(fileName)) {        // 실행파일인경우
        return 0;
    } else if (fileSize > maxSize) { 
        return 0;
    // } else if (!regExpImg.test(fileName)) {     // 이미지 파일이 아닌 경우
    //     return 0;
    } else {
        return 1;
    }
}

// 첨부파일에 따라서 체크하여 등록 가능한지 확인
document.addEventListener('change', e => {
    if (e.target.id =='file') {
        // 첨부되지 말아야 하는 파일이 들어왔을 떄 전송되는 것을 방지
        document.getElementById('regBtn').disabled = false;
        const fileObject = document.getElementById('file').files;
        
        let div = document.getElementById('fileZone');
        div.innerHTML = '';

        let ul = `<ul>`;
        let isOk = 1;       // fileSizeValidation의 통과 여부를 체크

        for (const file of fileObject) {
            let validResult = fileSizeValidation(file.name, file.size)
            isOk *= validResult;        // 모든 첨부파일의 결과가 1이여야 통과
            ul += `<li>`;
            // 업로드 가능 표시, 1 = true / 0 = false
            ul += `<div>${validResult ? '업로드 가능' : '업로드 불가능'}</div>`;
            ul += `${file.name} (<span>${file.size}Bytes</span>)</li>`;
        }

        ul += `</ul>`;
        div.innerHTML = ul;

        if (isOk == 0) {
            document.getElementById('regBtn').disabled = true;
         }
    }
});