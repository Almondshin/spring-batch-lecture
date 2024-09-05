package io.springbatch.springbatchlecture;

import java.util.*;

public class Solution {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */

        //입력 받은 String S 는 4가지 타입을 따라야 한다.
        // 추가 요청은 int + " " + object 타입을 따르고,
        // 1. append 추가하다 String W를 S문자열 맨뒤에
        // 2. delete 삭제하다 입력받은 int 값만큼 뒤에서부터 삭제시킴킴
        // 3. print 출력한다. 해당 인덱스의 캐릭터셋을 출력한다.
        // 4. undo 되돌린다. 되돌리지 않은 작업중 마지막 작업을 되돌린다.
        //           취소 작업 후, S는 마지막 작업이 적용되기 전의 상태로 되돌립니다.

        Scanner scanner = new Scanner(System.in);
        int Q = scanner.nextInt();
        scanner.nextLine();
        ArrayList<String> ops = new ArrayList<>();

        for (int i = 0; i < Q; i++) {
            String input = scanner.nextLine();
            ops.add(input);
        }

        Solution solution = new Solution();
        solution.checkingOperation(ops);
    }

    public void checkingOperation(ArrayList<String> ops) {
        // temp[0].equals("4")라면 이전 단계로 복원해야 하는데, 이전단계로 복원하려면
        // map 또는 Stack에 담아서 저장해두고 위로 팝업 시켜서 꺼내는 방식을 채택할 것 같다
        // Stack에 어떤 형태로 담아야 할까,
        Stack<String> history = new Stack<>();
        String S = "";
        history.push(S);
        for (String op : ops) {
            String[] parts = op.split(" ");
            String command = parts[0];

            switch (command) {
                case "1":
                    String W = parts[1];
                    S = S + W;
                    history.push(S);
                    break;

                case "2":
                    int k = Integer.parseInt(parts[1]);
                    S = S.substring(0, S.length() - k);
                    history.push(S);
                    break;

                case "3":
                    int index = Integer.parseInt(parts[1]) - 1;
                    System.out.println(S.charAt(index));
                    break;

                case "4":
                    if (history.size() > 1) {
                    history.pop();
                    S = history.peek();
                    }
                    break;
            }
        }
    }

}
