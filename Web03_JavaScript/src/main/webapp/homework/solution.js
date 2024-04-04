
function StringShift(){
	let A, B;
	let res;
	for(let x = 0; x < A.length; x++){
		if(A.charAt(x) == B.charAt(0)){
			let sub = A.substring(x, A.length) + A.substring(0, x);
			if(sub == B){
				res = x;
				return Math.min(res, A	.length -res);
			}
		}
	}
	
}