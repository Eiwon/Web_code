
/*문자열 "hello"에서 각 문자를 오른쪽으로 한 칸씩 밀고 마지막 문자는 맨 앞으로 이동시키면 "ohell"이 됩니다. 이것을 문자열을 민다고 정의한다면 문자열 A와 B가 매개변수로 주어질 때, A를 밀어서 B가 될 수 있다면 밀어야 하는 최소 횟수를 return하고 밀어서 B가 될 수 없으면 -1을 return 하도록 solution 함수를 완성해보세요.

제한사항
0 < A의 길이 = B의 길이 < 100
A, B는 알파벳 소문자로 이루어져 있습니다.*/

function solution(A, B) {
    var answer = 0;
   	for(let i = 0; i < B.length(); i++){
		   if(A.charAt(0) == B.charAt(i)){
			   // i를 기준으로 앞부분을 잘라서 뒤에 붙이고 비교
		   }
	   }
    return answer;
}