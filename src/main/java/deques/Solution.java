package deques;

import java.util.Stack;

public class Solution {
    public void main(String[] args){
    String[] commands = new String[] {"cd users", "cd codesignals", "cd ..", "cd admin"};
    String dir = "";
    Stack<String> stack = new Stack<>();
    for(int i = 0; i < commands.length; i++){
        String[] comms = commands[i].split(" ");
        if(comms[0] == "cd" && comms[1] != ".."){
            stack.push(comms[1]);
        }
        if(comms[0] == "cd" && comms[1] == ".."){
            stack.pop();
        }
        if(comms[0] == "cd" && comms[1] == "/"){
            stack = new Stack<String>();
        }

    }
    while(!stack.isEmpty()){
        dir = "/"+stack.pop() + dir;
    }
    System.out.println(dir);
}

}
