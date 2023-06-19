document.getElementsByName("id")[0].addEventListener('change', e => {
    console.log(e.target.value);

    const submitBtn = document.getElementById("submitBtn");
    const id = e.target.value;

    checkId(id).then(result => {
        if (result == 0) {
            submitBtn.innerHTML = '아이디 중복';
            submitBtn.disabled = true;
            submitBtn.style.backgroundColor = "#dc3545";
        } else {
            submitBtn.innerHTML = '회원가입';
            submitBtn.disabled = false;
            submitBtn.style.backgroundColor = "#03c75a";
        }
    });
});

async function checkId(id) {
    try {
        const url = '/user/signup_check';
        const config = {
            method: "post",
            headers: {
                'Content-Type': 'application/json; charset=UTF-8'
            },
            body: JSON.stringify({
                id: id
            }),
        }
        const resp = await fetch(url, config);
        const result = await resp.text();
        return result;
    } catch (error) {
        console.log(error)
    }
}