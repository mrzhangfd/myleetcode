package lanqiao;


import java.util.ArrayList;
import java.util.List;

/**
 * @author icatzfd
 * Created on 2020/5/31 16:36.
 */
public class Backtrack {

    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        backtrack(0, nums, res, new ArrayList<Integer>());
        return res;
    }

    private void backtrack(int start, int[] nums, List<List<Integer>> res, ArrayList<Integer> temlist) {

        res.add(new ArrayList<>(temlist));

        for (int i = start; i < nums.length; i++) {
            temlist.add(nums[i]);
            backtrack(i + 1, nums, res, temlist);
            temlist.remove(temlist.size() - 1);
        }

    }

    public static void main(String[] args) {
        Backtrack backtrack = new Backtrack();

        int[] nums = {3, 4, 5};
        System.out.println(backtrack.subsets(nums));
    }




    /*public boolean exist(char[][] board, String word) {
        boolean[][] visited = new boolean[board.length][board[0].length];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (word.charAt(0) == board[i][j] && backtrack(i, j, 0, word, visited, board)) {
                    return true;
                }
            }
        }
        return false;

    }

    private boolean backtrack(int i, int j, int idx, String word, boolean[][] visited, char[][] board) {
        if (idx == word.length()) {
            return true;
        }
        if (i >= board.length || i < 0 || j >= board[0].length || j < 0
                || board[i][j] != word.charAt(idx) || visited[i][j]) {
            return false;
        }
        visited[i][j] = true;
        if (backtrack(i + 1, j, idx + 1, word, visited, board)
                || backtrack(i - 1, j, idx + 1, word, visited, board)
                || backtrack(i, j + 1, idx + 1, word, visited, board)
                || backtrack(i, j - 1, idx + 1, word, visited, board)) {
            return true;
        }
        visited[i][j] = false; // 回溯
        return false;
    }*/
}
