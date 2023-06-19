// 댓글 등록
async function postCommentToServer(cmtData) {
	try {
		const url = '/comment/post';
		const config = {
			method: "post",
			headers: {
				'content-type': 'application/json;charset=utf-8',
			},
			body: JSON.stringify(cmtData),
		}
		const resp = await fetch(url, config);
		const result = await resp.text();
		return result;
	} catch (error) {
		console.log(error);
	}
}

document.getElementById('cmtPostBtn').addEventListener('click', () => {
	const cmtText = document.getElementById('cmtText').value;
	// console.log("cmtText : " + cmtText);

	if (cmtText == "" || cmtText == null) {
		alert("댓글을 입력해주세요");
		document.getElementById('cmtText').focus();
		return false;
	} else {
		let cmtData = {
			bno : bnoVal,
			writer : document.getElementById('cmtWriter').innerText,
			content : cmtText
		};
		// console.log(cmtData);

		postCommentToServer(cmtData).then(result => {
			if (result > 0) {
				alert("댓글 등록 성공");
				getCommentList(cmtData.bno);
			} 
		});
	}
});

// 댓글 출력
async function spreadCommentFromServer(bno) {
	try {
		const resp = await fetch('/comment/list/' + bno);
		const result = await resp.json();
		return result;
	} catch (error) {
		console.log(error);
	}
}

function getCommentList(bno) {
	spreadCommentFromServer(bno).then(result => {
		// console.log("result : " + result);

		const ul = document.getElementById('cmtListArea');
		ul.innerHTML = "";

		if (result.length > 0) { 

			for (let cvo of result) {
				let li = `
					<li data-cno="${cvo.cno}">
						<div>
							<div>${cvo.writer}</div>
							<input type="text" class="cmtTextMod" value="${cvo.content}">
							<button type="button" data-cno="${cvo.cno}" class="cmtModBtn">수정</button>
							<button type="button" data-cno="${cvo.cno}" class="cmtDelBtn">삭제</button>
						</div>
						<span>${cvo.regdate}</span>
					</li>`;
				ul.innerHTML += li;
			}
		} else {
			let li = `<li>Comment List Empty</li>`;
			ul.innerHTML += li;
		}
	});
}

// 댓글 삭제
async function removeCommentFromServer(cno) {
	try {
		const uri = "/comment/remove/" + cno;
		const config = {
			method : "delete"
		};
		const resp = await fetch(uri, config);
		const result = await resp.text();
		return result;
	} catch (error) {
		console.log(error);
	} 
}

// 댓글 수정
async function modifyCommentFromServer(cmtDataMod) {
	try {
		const uri = "/comment/modify";
		const config = {
			method : "put",
			headers : {
				"content-type" : "application/json; charset=UTF-8"
			},
			body : JSON.stringify(cmtDataMod)
		}

		const resp = await fetch(uri, config);
		const result = await resp.text();
		return result;
	} catch (error) {
		console.log(error);
	}
}

document.addEventListener('click', e => {
	// console.log(bnoVal);

	if (e.target.classList.contains('cmtDelBtn')) {
		let li = e.target.closest('li');
		let cno = li.dataset.cno;

		removeCommentFromServer(cnoVal).then(result => {
			// console.log("result : " + result);

			if (result > 0) {
				alert("댓글 삭제 완료");
				getCommentList(bnoVal);	
			} else {
				alert("댓글 삭제 실패");
			}
		});
	}

	if (e.target.classList.contains('cmtModBtn')) {
		let li = e.target.closest('li');
		let cno = li.dataset.cno;
		let content = li.querySelector('.cmtTextMod').value;
		let cmtDataMod = {
			cno: cno,
			content: content
		}

		modifyCommentFromServer(cmtDataMod).then(result => {
			if (result > 0) {
				alert("댓글 수정 완료");
				getCommentList(bnoVal);
			} else {
				alert("댓글 수정 실패");
			}
		});
	}
});