document.getElementById("inputPw").addEventListener('change', e=> {
    console.log("inputPw");
    
    const submitBtn = document.getElementById("submitBtn");
    const id = document.getElementById("inputId").value;
    const pw = document.getElementById("inputPw").value;

    checkPw(id, pw).then(result => {
        if (result > 0) {
            submitBtn.innerHTML = '회원수정';
            submitBtn.disabled = false;
            submitBtn.style.backgroundColor = "#03c75a";
        } else if (result == 0) {
            submitBtn.innerHTML = '잘못된 비밀번호';
            submitBtn.disabled = true;
            submitBtn.style.backgroundColor = "#dc3545";
        }
    });
});

async function checkPw(id, pw) {
    try {
        const url = '/user/modify_check';
        const config = {
            method: "post",
            headers: {
                'Content-Type': 'application/json; charset=UTF-8'
            },
            body: JSON.stringify({
                id: id,
                pw: pw
            }),
        }
        const resp = await fetch(url, config);
        const result = await resp.text();
        return result;
    } catch (error) {
        console.log(error)
    }
}