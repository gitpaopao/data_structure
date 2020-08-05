package pp.linked_list;


import java.util.Stack;

public class SumList {

    public static class Node {
        int data;
        Node next;
        Node (int data){
            this.data = data;
        }
    }


    public static Integer sum(Node node){
        if (node == null){
            return null;
        }

        int sum = 0;
        while (node != null){
           sum = sum * 10 + node.data;
           node = node.next;
        }
        return sum;
    }

    public static Node intToList(Integer value){

        if (value == null){
            return null;
        }

        Stack<Integer> stack = new Stack<>();

        while (value != null){
            Integer ele = value % 10;
            stack.push(ele);
            value = value / 10;
            if (value == 0){
                break;
            }
        }

        return create(stack);

    }

   static Node create(Stack<Integer> stack){
        if (stack.isEmpty()){
            return null;
        }

        Integer pop = stack.pop();
        Node node = new Node(pop);
        node.next = create(stack);

        return node;
    }

    public static Node sumList(Node head1,Node head2){
        Integer sum = sum(head1);
        Integer sum1 = sum(head2);

        if (sum == null && sum1 == null){
            return null;
        }

        int count = 0;
        if (sum != null){
            count+=sum;
        }
        if (sum1 != null){
            count+=sum1;
        }

        return intToList(count);
    }

    public static void main(String[] args) {

        Node node = new Node(1000000000);
        node.next = new Node(2);

        Node node1 = new Node(1);
        node1.next = new Node(2);

        Node result = sumList(node, node1);
        System.out.println(result.data);
    }
}
