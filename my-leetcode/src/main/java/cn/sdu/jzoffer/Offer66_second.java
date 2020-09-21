package cn.sdu.jzoffer;

import cn.sdu.ListNode;
import cn.sdu.TreeNode;

import javax.swing.plaf.nimbus.State;
import java.util.*;


/**
 * 二刷
 *
 * @author icatzfd
 * Created on 2020/6/27 10:51.
 */
public class Offer66_second {

    //矩阵中的路径
    public boolean hasPath(char[] matrix, int rows, int cols, char[] str) {

        if (matrix == null || matrix.length == 0 || rows == 0 || cols == 0 || str == null || str.length == 0) {
            return false;
        }
        char[][] mat = new char[rows][cols];
        int k = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                mat[i][j] = matrix[k++];
            }
        }
        boolean[][] visited = new boolean[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (mat[i][j] == str[0]) {

                    return hasPathHelper(i, j, 0, str, mat, visited);
                }
            }
        }

        return false;
    }

    private boolean hasPathHelper(int i, int j, int start, char[] str, char[][] mat, boolean[][] visited) {

        if (i < 0 || i >= mat.length || j < 0 || j >= mat[0].length) {
            return false;
        }
        if (start >= str.length) {
            return false;
        }
        if (visited[i][j]) {
            return false;
        }
        if (mat[i][j] != str[start]) {
            return false;
        }
        if (start == str.length - 1) {
            return true;
        }
        visited[i][j] = true;
        boolean res = hasPathHelper(i + 1, j, start + 1, str, mat, visited) |
                hasPathHelper(i, j + 1, start + 1, str, mat, visited) ||
                hasPathHelper(i - 1, j, start + 1, str, mat, visited) ||
                hasPathHelper(i, j - 1, start + 1, str, mat, visited);
        //回溯
        visited[i][j] = false;
        return res;
    }

    //旋转数组的最小数，
    public int minNumberInRotateArray(int[] array) {
        if (array.length == 0) {
            return 0;
        }
        int l = 0;
        int h = array.length - 1;
        while (l < h) {
            int mid = l + (h - l) / 2;
            //此处需要和array[h]相比
            //如果和array[l] 比较，若出现 1 ，2 ，3 ，4， 5 这种情况。则不行。
            if (array[mid] > array[h]) {
                l = mid + 1;
            } else if (array[mid] < array[h]) {
                h = mid;
            } else if (array[mid] == array[h]) {
                l++;
            }
        }
        return array[l];

    }

    //矩形覆盖
    public int RectCover(int target) {
        if (target == 0) {
            return 0;
        } else if (target == 1) {
            return 1;
        } else if (target == 2) {
            return 2;
        }
        int a = 1;
        int b = 2;
        int c = 0;
        for (int i = 3; i <= target; i++) {
            c = a + b;
            a = b;
            b = c;
        }
        return c;


    }

    //二进制中1的个数
    //移位：有符号数移位时，按照补码的形式移位
    //右移：最右边的一位舍弃，最左边补符号位。
    //
    //左移：最左边的一位舍弃，最右边补0。
    public int NumberOf1(int n) {
        int res = 0;
        while (n != 0) {
            n &= (n - 1);
            res++;
        }
        return res;
    }


    //栈的压入、弹出序列
    public boolean IsPopOrder(int[] pushA, int[] popA) {
        Stack<Integer> stack = new Stack<>();
        int j = 0;
        for (int i = 0; i < pushA.length; i++) {
            stack.push(pushA[i]);
            while (stack.peek() == popA[j]) {
                stack.pop();
                j++;
            }
        }
        if (stack.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    //从上往下打印二叉树
    public ArrayList<Integer> PrintFromTopToBottom(TreeNode root) {
        ArrayList<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {


            TreeNode treeNode = queue.poll();
            res.add(treeNode.val);
            if (treeNode.left != null) {
                queue.offer(treeNode.left);
            }
            if (treeNode.right != null) {
                queue.offer(treeNode.right);
            }
        }

        return res;
    }

    //二叉搜索树的后序遍历序列
    public boolean VerifySquenceOfBST(int[] sequence) {
        if (sequence == null || sequence.length == 0) {
            return false;
        }
        return helper(0, sequence.length - 1, sequence);
    }

    private boolean helper(int start, int end, int[] sequence) {
        if (end - start <= 1) {
            return true;
        }
        int rootVal = sequence[end];
        int curidx = start;
        while (sequence[curidx] < rootVal && curidx < end) {
            curidx++;
        }

        for (int i = curidx; i < end; i++) {
            if (sequence[i] < rootVal) {
                return false;
            }
        }
        return helper(start, curidx - 1, sequence) && helper(curidx, end - 1, sequence);
    }

    //二叉树中和为某一值的路径

    //二叉搜索树转为双向链表

    //最小的k个数，用大根堆，堆排序
    public ArrayList<Integer> GetLeastNumbers_Solution_queque(int[] input, int k) {
        ArrayList<Integer> res = new ArrayList<>();
        Queue<Integer> queue = new PriorityQueue<Integer>(11, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2.compareTo(o1);
            }
        });

        for (int i = 0; i < input.length; i++) {
            if (queue.size() == k) {
                if (input[i] < queue.peek()) {
                    queue.poll();
                    queue.offer(input[i]);
                }
            } else {
                queue.offer(input[i]);

            }

        }
        Math.max(1, 2);
        while (!queue.isEmpty()) {
            res.add(queue.poll());
        }
        return res;
    }

    //最小的k个数 快排
    public ArrayList<Integer> GetLeastNumbers_Solution(int[] input, int k) {
        ArrayList<Integer> res = new ArrayList<>();
        if (input == null || input.length == 0) {
            return res;
        }
        quickSort(input, 0, input.length - 1);
        if (k > input.length) {
            return res;
        }
        for (int i = 0; i < k; i++) {
            res.add(input[i]);
        }
        return res;
    }

    private void quickSort(int[] nums, int left, int right) {
        if (left < right) {
            int idx = partition(nums, left, right);
            quickSort(nums, left, idx - 1);
            quickSort(nums, idx + 1, right);
        }
    }

    //严蔚敏的标准分割函数
    private int partition(int[] nums, int left, int right) {
        int pivot = nums[left];
        while (left < right) {
            while (left < right && nums[right] >= pivot) {
                right--;
            }
            nums[left] = nums[right];
            while (left < right && nums[left] <= pivot) {
                left++;
            }
            nums[right] = nums[left];

        }
        nums[left] = pivot;
        return left;
    }

    private static void swap(int[] nums, int left, int right) {
        int t = nums[left];
        nums[left] = nums[right];
        nums[right] = t;
    }

    public int FindGreatestSumOfSubArray(int[] array) {

        int len = array.length;
        int[] dp = new int[len];
        dp[0] = array[0];
        int max = Integer.MIN_VALUE;
        for (int i = 1; i < len; i++) {
            dp[i] = Math.max(dp[i - 1] + array[i], array[i]);
            if (dp[i] > max) {
                max = dp[i];
            }
        }
        return max;
    }

    //数字在排序数组中出现的次数
    public int GetNumberOfK(int[] array, int k) {
        if (array == null || array.length == 0) {
            return 0;
        }
        if (k > array[array.length - 1] || k < array[0]) {
            return 0;
        }
        System.out.println(findIdx(array, k + 0.5));
        System.out.println(findIdx(array, k - 0.5));
        return findIdx(array, k + 0.5) - findIdx(array, k - 0.5);
    }

    private int findIdx(int[] array, double k) {
        int i = 0;
        int j = array.length - 1;
        int mid;
        while (i <= j) {
            mid = i + (j - i) / 2;
            if (array[mid] > k) {
                j = mid - 1;
            } else if (array[mid] < k) {
                i = mid + 1;
            }
        }
        return j;
    }

    //是否是顺子
    public boolean isStraight(int[] numbers) {
        if (numbers == null || numbers.length != 5) {
            return false;
        }
        Arrays.sort(numbers);
        int len = numbers.length;
        int firsrNumNot0 = 0;
        for (int i = 0; i < len - 1; i++) {
            if (numbers[i] != 0) {
                firsrNumNot0 = numbers[i];
                break;
            }
        }
        if (numbers[4] - firsrNumNot0 > 5) {
            return false;
        }
        int numOf0 = 0;
        for (int num : numbers) {
            if (num == 0) {
                numOf0++;
            }
        }
        int needNumOf0 = 0;
        for (int i = 4; i > 0 && numbers[i - 1] != 0; i--) {
            int dis = numbers[i] - numbers[i - 1];
            needNumOf0 += dis - 1;
            if (dis == 0) {
                return false;
            }
        }
        if (needNumOf0 > numOf0) {
            return false;
        }
        return true;
    }

    //滑动窗口的最大值
    public ArrayList<Integer> maxInWindows2(int[] num, int size) {
        if (num == null || num.length == 0 || size <= 0 || size > num.length) {
            return new ArrayList<>();
        }
        ArrayList<Integer> list = new ArrayList<>();
        if (size == num.length) {
            int maxIdx = findMaxIdx(num, 0, num.length - 1);
            list.add(num[maxIdx]);
            return list;
        }
        //左边界
        int left = 0;
        //右边界
        int right = size - 1;
        while (right < num.length) {
            int temp = findMaxIdx(num, left, right);
            list.add(num[temp]);
            left++;
            right++;
        }

        return list;
    }

    private int findMaxIdx(int[] num, int left, int right) {
        int maxIdx = left;
        for (int i = left; i <= right; i++) {
            if (num[i] > num[maxIdx]) {
                maxIdx = i;
            }
        }
        return maxIdx;
    }

    public ArrayList<Integer> maxInWindows1(int[] num, int size) {
        ArrayList<Integer> list = new ArrayList<>();
        if (num == null || num.length < size || size == 0) {
            return list;
        }
        //队列头部存的是当前窗口的最大值的下标
        LinkedList<Integer> linkedList = new LinkedList<>();
        for (int i = 0; i < num.length; i++) {
            //队列不为空，同时队列尾部的位置对应的值小于当前值
            while (!linkedList.isEmpty() && num[linkedList.peekLast()] < num[i]) {
                linkedList.pollLast();
            }
            linkedList.add(i);

            //如果队列头存储的位置处于窗口外，则丢弃。
            if (linkedList.peekFirst() <= (i - size)) {
                linkedList.pollFirst();
            }
            //当i>=size-1时候，窗口内的值才满足size个
            if (i >= size - 1) {
                list.add(num[linkedList.peekFirst()]);
            }
        }
        return list;
    }

    //剪绳子
    public static int cutRope(int target) {
        if (target <= 3 && target > 1) {
            return target - 1;
        }

        int res = 1;
        while (target > 4) {
            res *= 3;
            target -= 3;
        }
        res *= target;
        return res;
    }

    //二叉树中和为某一值的路径
    //回溯
    public ArrayList<ArrayList<Integer>> FindPath(TreeNode root, int target) {
        if (root == null) {
            return new ArrayList<>();
        }
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        ArrayList<Integer> path = new ArrayList<>();
        findPathHelper(res, path, target, root);

        return res;
    }

    private void findPathHelper(ArrayList<ArrayList<Integer>> res, ArrayList<Integer> path, int target, TreeNode node) {
        if (node == null) {
            return;
        }
        target -= node.val;
        path.add(node.val);
        if (node.left == null && node.right == null && target == 0) {
            res.add(new ArrayList<>(path));
        }

        findPathHelper(res, path, target, node.left);
        findPathHelper(res, path, target, node.right);
        path.remove(path.size() - 1);

    }

    //对称的二叉树
    boolean isSymmetrical(TreeNode pRoot) {
        if (pRoot == null) {
            return true;
        }
        return isSymmetricalHelper(pRoot.left, pRoot.right);
    }

    private boolean isSymmetricalHelper(TreeNode left, TreeNode right) {
        if (left == null && right == null) {
            return true;
        }
        if (left != null && right != null) {
            return left.val == right.val && isSymmetricalHelper(left.left, right.right) &&
                    isSymmetricalHelper(left.right, right.left);
        } else {
            return false;
        }
    }


    //二叉搜索双向链表
    public TreeNode Convert(TreeNode pRootOfTree) {

        if (pRootOfTree == null) {
            return null;
        }
        ArrayList<TreeNode> list = new ArrayList<>();
        ConvertHelper(pRootOfTree, list);
        return Convert(list);
    }

    private void ConvertHelper(TreeNode pRootOfTree, ArrayList<TreeNode> list) {
        if (pRootOfTree.left != null) {
            ConvertHelper(pRootOfTree.left, list);
        }
        list.add(pRootOfTree);
        if (pRootOfTree.right != null) {
            ConvertHelper(pRootOfTree.right, list);
        }

    }

    private TreeNode Convert(ArrayList<TreeNode> list) {
        for (int i = 0; i < list.size() - 1; i++) {
            list.get(i).right = list.get(i + 1);
            list.get(i + 1).left = list.get(i);
        }
        return list.get(0);
    }

    // 删除链表中重复节点
    public ListNode deleteDuplication(ListNode pHead) {
        if (pHead == null || pHead.next == null) {
            return pHead;
        }

        ListNode dummyHead = new ListNode(-1);
        dummyHead.next = pHead;
        ListNode pre = dummyHead;
        ListNode cur = pHead;
        ListNode next = cur.next;
        boolean flag;
        while (next != null) {
            flag = false;
            while (next != null && cur.val != next.val) {
                pre = cur;
                cur = next;
                next = cur.next;
            }

            while (next != null && next.val == cur.val) {
                flag = true;
                next = next.next;
            }

            if (next == null) {
                if (flag) {
                    pre.next = null;
                }


            } else {
                pre.next = next;
                cur = next;
                next = cur.next;

            }
        }

        return dummyHead.next;

    }

    //删除链表中重复的节点
    public ListNode deleteDuplication2(ListNode pHead) {
        if (pHead == null || pHead.next == null) {
            return pHead;
        }

        ListNode dummyHead = new ListNode(-1);
        dummyHead.next = pHead;
        ListNode cur = dummyHead;
        ListNode next = dummyHead.next;
        while (next != null) {
            if (next.next != null && next.val == next.next.val) {
                while (next.next != null && next.val == next.next.val) {
                    next = next.next;
                }
                cur = cur.next;
                next = next.next;
            } else {
                cur = cur.next;
                next = next.next;
            }
        }
        return dummyHead.next;
    }


    //把数组排成最小的数
    public String PrintMinNumber(int[] numbers) {
        if (numbers == null || numbers.length == 0) {
            return "";
        }
        List<Integer> list = new ArrayList<>();
        for (int num : numbers) {
            list.add(num);
        }

        Comparator cmp = new MyComparator();
        //Arrays.sort(numbers, cmp);
        Collections.sort(list, cmp);
        String s = "";
        for (int num : list) {
            s += num;
        }

        return s;

    }

    class MyComparator implements Comparator<Integer> {
        @Override
        public int compare(Integer o1, Integer o2) {
            String s1 = o1 + "" + o2;
            String s2 = o2 + "" + o1;
            return s1.compareTo(s2);

        }


    }

    //丑数
    public int GetUglyNumber_Solution(int index) {
        if (index < 1) {
            return 0;
        }
        if (index == 1) {
            return 1;
        }
        // 1 2 3 4 5 6 8 10 12 。。。。
        //后面的丑数是由前面的丑数乘以2 或3  或5  ，然后选取最小值得来的。

        //相当于合并三个有序列表
        //第一个列表为 所有丑数乘以2组成，第二个列表为所有丑数乘以3组成，第三个列表为所有丑数乘以5组成
        // idx2  idx3  idx5 表示三个指针 分别指向三个列表头元素，每次选取三个列表头元素的最小值 加入结果数组。
        //然后该最小元素对应的队列指针移动
        //用idx2来控制 2 乘以ugly数组的第idx2个元素 即2*ugly[idx2]
        int idx2 = 0, idx3 = 0, idx5 = 0;
        int[] ugly = new int[index];
        ugly[0] = 1;
        for (int i = 1; i < index; i++) {
            int temp = Math.min(2 * ugly[idx2], Math.min(3 * ugly[idx3], 5 * ugly[idx5]));
            if (temp == 2 * ugly[idx2]) {
                idx2++;
            }
            if (temp == 3 * ugly[idx3]) {
                idx3++;
            }
            if (temp == 5 * ugly[idx5]) {
                idx5++;
            }
            ugly[i] = temp;

        }
        return ugly[index - 1];
    }

    //调整数组顺序 使奇数位于偶数前面
    public static void reOrderArray(int[] array) {
        if (array == null || array.length == 0) {
            return;
        }

        for (int i = 0; i < array.length; i++) {
            boolean flag = false;
            for (int j = 0; j < array.length - 1; j++) {
                if (array[j] % 2 == 0 && array[j + 1] % 2 == 1) {
                    flag = true;
                    swap(array, j, j + 1);
                }
            }
            if (!flag) {
                break;
            }
        }
        System.out.println(Arrays.toString(array));
    }


    //jz2 替换空格
    public String replaceSpace(StringBuffer str) {
        StringBuffer res = new StringBuffer();
        String ss = str.toString();
        for (int i = 0; i < ss.length(); i++) {
            if (ss.charAt(i) != ' ') {
                res.append(ss.charAt(i));
            } else {
                res.append("%20");
            }
        }
        return res.toString();
    }

    //jz3 从尾到头打印链表
    public static ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
        ArrayList<Integer> res = new ArrayList<>();
        if (listNode == null) {
            return res;
        }
        printListFromTailToHeadHelper(listNode, res);
        return res;
    }

    private static void printListFromTailToHeadHelper(ListNode listNode, ArrayList<Integer> res) {

        if (listNode.next != null) {
            printListFromTailToHeadHelper(listNode.next, res);
        }
        res.add(listNode.val);

    }

    //数值的整数次方
    public static double Power(double base, int exponent) {
        if (base == 0) {
            return 0;
        }
        if (exponent < 0 && base == 0) {
            throw new RuntimeException("分母不能为0");
        }
        boolean flag = false;
        if (exponent < 0) {
            flag = true;
            exponent = -exponent;
        }
        double res = 1;
        while (exponent != 0) {
            if ((exponent & 1) == 1) {
                res *= base;
            }
            base *= base;
            exponent = exponent >> 1;
        }
        return flag ? 1 / res : res;
    }

    //倒数第k个节点
    public ListNode FindKthToTail(ListNode head, int k) {
        if (head == null || k == 0) {
            return null;
        }

        ListNode fast = head;
        ListNode low = head;

        while (k != 1) {
            k--;
            fast = fast.next;
            if (fast == null) {
                return null;
            }
        }

        while (low.next != null && fast.next != null) {
            low = low.next;
            fast = fast.next;
        }
        return low;


    }

    //反转链表
    public ListNode ReverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode pre = null;
        ListNode next;
        while (head != null) {
            next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;


    }

    //合并两个排序的链表
    public ListNode Merge(ListNode list1, ListNode list2) {
        if (list1 == null && list2 == null) {
            return null;
        }

        ListNode dummyHead = new ListNode(-1);
        ListNode cur = dummyHead;
        while (list1 != null || list2 != null) {
            if (list1 == null) {
                cur.next = list2;
                break;
            }
            if (list2 == null) {
                cur.next = list1;
                break;
            }
            cur.next = list1.val <= list2.val ? list1 : list2;
            if (cur.next == list1) {
                list1 = list1.next;
            } else {
                list2 = list2.next;
            }


            cur = cur.next;
        }
        return dummyHead.next;
    }

    //合并k个升序链表
    public ListNode mergeKLists(ListNode[] lists) {
        ListNode dummyHead = new ListNode(-1);
        ListNode cur = dummyHead;
        if (lists == null || lists.length == 0) {
            return null;
        }
        int minIdx = 0;
        int count;
        int min = Integer.MAX_VALUE;
        while (true) {
            count = 0;
            min = Integer.MAX_VALUE;
            for (int i = 0; i < lists.length; i++) {
                if (lists[i] == null) {
                    count++;
                    continue;
                }
                if (lists[i].val < min) {
                    min = lists[i].val;
                    minIdx = i;
                }
            }
            if (count == lists.length) {
                break;
            }
            cur.next = lists[minIdx];
            cur = cur.next;
            lists[minIdx] = lists[minIdx].next;
        }
        return dummyHead.next;


    }

    //树的子结构
    public boolean HasSubtree(TreeNode root1, TreeNode root2) {
        boolean flag = false;
        if (root1 != null && root2 != null) {
            if (root1.val == root2.val) {
                flag = hasSubtreeHelper(root1.left, root2.left) && hasSubtreeHelper(root1.right, root2.right);
            }
            if (!flag) {
                flag = HasSubtree(root1.left, root2) || HasSubtree(root1.right, root2);
            }
        }
        return flag;
    }

    private boolean hasSubtreeHelper(TreeNode root1, TreeNode root2) {
        if (root1 == null && root2 != null) {
            return false;
        }
        if (root2 == null) {
            return true;
        }
        if (root1.val != root2.val) {
            return false;
        }

        return hasSubtreeHelper(root1.left, root2.left) && hasSubtreeHelper(root1.right, root2.right);

    }

    //滑动窗口最大值
    public static ArrayList<Integer> maxInWindows(int[] num, int size) {
        ArrayList<Integer> ret = new ArrayList<>();

        if (num == null || num.length == 0 || size <= 0 || size > num.length) {
            return null;
        }


        //用来存储当前窗口最大值索引
        Deque<Integer> queue = new LinkedList<>();

        for (int i = 0; i < num.length; i++) {
            while (!queue.isEmpty() && num[queue.peekLast()] < num[i]) {
                queue.pollLast();
            }
            queue.offer(i);
            //当队列的最后一个不在窗口中，就删除
            if (queue.peekFirst() <= i - size) {
                queue.pollFirst();
            }
            //当i<size -1 时候，窗口有可能不满
            if (i >= size - 1) {
                ret.add(num[queue.peekFirst()]);
            }
        }
        return ret;
    }


    //二叉树的镜像
    public static void Mirror(TreeNode root) {
        if (root == null) {
            return;
        }
        MirrorHelper(root);
    }

    private static TreeNode MirrorHelper(TreeNode node) {
        if (node == null) {
            return null;
        }
        TreeNode left = node.left;
        TreeNode right = node.right;
        node.right = MirrorHelper(left);
        node.left = MirrorHelper(right);
        return node;
    }


    //栈的压入、弹出序列
    public static boolean IsPopOrder1(int[] pushA, int[] popA) {
        if (popA.length != pushA.length) {
            return false;
        }
        Stack<Integer> stack = new Stack<>();
        int idx = 0;
        for (int i = 0; i < pushA.length; i++) {
            stack.push(pushA[i]);
            while (!stack.isEmpty() && popA[idx] == stack.peek()) {
                stack.pop();
                idx++;
            }
        }
        return idx == popA.length;

    }

    //二叉搜索树的后序遍历序列

    /**
     * BST的后序序列的合法序列是，对于一个序列S，最后一个元素是x （也就是根），如果去掉最后一个元素的序列为T，
     * 那么T满足：T可以分成两段，前一段（左子树）小于x，后一段（右子树）大于x，且这两段（子树）都是合法的后序序列。完美的递归定义 : ) 。
     *
     * @param sequence
     * @return
     */
    public static boolean VerifySquenceOfBST1(int[] sequence) {
        if (sequence == null || sequence.length == 0) {
            return true;
        }
        int len = sequence.length;
        return judge(sequence, 0, len - 1);
    }

    private static boolean judge(int[] sequence, int start, int end) {
        if (start >= end) {
            return true;
        }
        int idx = start;
        while (idx < end && sequence[idx] < sequence[end]) {
            idx++;
        }
        for (int i = idx; i <= end; i++) {
            if (sequence[i] < sequence[end]) {
                return false;
            }
        }


        return judge(sequence, start, idx - 1) & judge(sequence, idx, end - 1);
    }

    //二叉树中和为某一值的路径
    public List<List<Integer>> FindPath2(TreeNode root, int target) {

        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        ArrayList<Integer> path = new ArrayList<>();
        findPathHelper2(root, target, path, res);

        return res;
    }

    private void findPathHelper2(TreeNode node, int target, ArrayList<Integer> path, List<List<Integer>> res) {
        if (node == null) {
            return;
        }
        target -= node.val;
        path.add(node.val);
        if (target == 0 && node.left == null && node.right == null) {
            res.add(new ArrayList<>(path));

        }
        findPathHelper2(node.left, target, path, res);
        findPathHelper2(node.right, target, path, res);
        path.remove(path.size() - 1);

    }

    //二叉搜索树与双向链表
    public TreeNode Convert1(TreeNode pRootOfTree) {


        if (pRootOfTree == null) {
            return null;
        }
        List<TreeNode> list = new ArrayList<>();
        convertHelper(pRootOfTree, list);
        for (int i = 0; i < list.size(); i++) {
            if (i == 0) {
                list.get(i).right = list.get(i + 1);

            } else if (i == list.size() - 1) {

                list.get(i).left = list.get(i - 1);
            } else {
                list.get(i).left = list.get(i - 1);
                list.get(i).right = list.get(i + 1);
            }

        }
        return list.get(0);
    }

    private void convertHelper(TreeNode pRootOfTree, List<TreeNode> list) {
        if (pRootOfTree == null) {
            return;
        }
        convertHelper(pRootOfTree.left, list);
        list.add(pRootOfTree);
        convertHelper(pRootOfTree.right, list);
    }

    //字符串的排列
    public static String[] Permutation(String str) {
        Set<String> res = new HashSet<>();
        if (str == null || str.length() == 0) {
            return (String[]) res.toArray();
        }
        char[] chars = str.toCharArray();


        permutationHelper(res, chars, 0);
        String[] rr = res.toArray(new String[res.size()]);
        Arrays.sort(rr);

        return rr;
    }

    private static void permutationHelper(Set<String> res, char[] chars, int start) {
        if (start == chars.length - 1) {
            String temp = new String(chars);
            res.add(temp);
            return;
        }
        for (int i = start; i < chars.length; i++) {
            swapStr(chars, i, start);
            permutationHelper(res, chars, start + 1);
            swapStr(chars, i, start);

        }

    }

    private static void swapStr(char[] chars, int i, int start) {
        char temp = chars[i];
        chars[i] = chars[start];
        chars[start] = temp;
    }


    public int MoreThanHalfNum_Solution(int[] nums) {
        int flag = nums[0];
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            if (sum == 0) {
                flag = nums[i];
                sum++;
            } else {
                if (nums[i] != flag) {
                    sum--;
                } else {
                    sum++;
                }
            }
        }

        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == flag) {
                count++;
            }
        }
        if (count > nums.length / 2) {
            return flag;
        } else {
            return 0;
        }


    }

    //最小的k个数
    public ArrayList<Integer> GetLeastNumbers_Solution1(int[] input, int k) {
        return null;
    }

    //连续子数组的最大和
    public static int FindGreatestSumOfSubArray1(int[] array) {
        if (array == null || array.length == 0) {
            return -1;
        }
        int n = array.length;
        int[] dp = new int[n];
        dp[0] = array[0];
        int sum = 0;
        int max = Integer.MIN_VALUE;
        for (int i = 1; i < n; i++) {
            dp[i] = Math.max(array[i], dp[i - 1] + array[i]);
            max = Math.max(dp[i], max);

        }
        return max;
    }

    //把数组排成最小的数
    public static String PrintMinNumber1(int[] numbers) {
        Queue<Integer> queue = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                String s1 = String.valueOf(o1) + String.valueOf(o2);
                String s2 = String.valueOf(o2) + String.valueOf(o1);
                return s1.compareTo(s2);

            }
        });
        for (int i = 0; i < numbers.length; i++) {
            queue.offer(numbers[i]);
        }

        StringBuilder res = new StringBuilder();
        while (!queue.isEmpty()) {
            res.append(queue.poll());
        }
        return res.toString();
    }

    //丑数
    public static int GetUglyNumber_Solution1(int index) {
        if (index <= 0) {
            return 0;
        }
        int idx2 = 0;
        int idx3 = 0;
        int idx5 = 0;
        List<Integer> list = new ArrayList<>();
        list.add(1);
        while (list.size() < index) {
            int temp = Math.min(Math.min(list.get(idx2) * 2, list.get(idx3) * 3), list.get(idx5) * 5);
            list.add(temp);
            if (temp == list.get(idx2) * 2) {
                idx2++;
            }
            if (temp == list.get(idx3) * 3) {
                idx3++;
            }
            if (temp == list.get(idx5) * 5) {
                idx5++;
            }
        }
        return list.get(index - 1);
    }

    //第一次只出现一次的字符
    public int FirstNotRepeatingChar(String str) {
        if (str == null || str.length() == 0) {
            return -1;
        }
        int[] chars = new int[58];
        for (int i = 0; i < str.length(); i++) {
            char temp = str.charAt(i);
            chars[temp - 'a']++;
        }
        int first=-1;
        for (int i = 0; i < 128; i++) {
            if(chars[i]==1){

            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] arrs = {332, 321};
        System.out.println(Integer.valueOf('A'));
        System.out.println(Integer.valueOf('Z'));
        System.out.println(Integer.valueOf('a'));
        System.out.println(Integer.valueOf('z'));

    }
}
