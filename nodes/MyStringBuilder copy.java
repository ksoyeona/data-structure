/** Soyeon Kim, cs8bwaha
 *3/8/18
 *
 *This file contains a class MyStringBuilder which constructs MyStringBuilder
 *objects and main to test the functionality of each methods.
 */

/** MyStringBuilder objects contains CharacterNode objects which stores a char 
 * and a pointer to another CharacterNode object.
 */
public class MyStringBuilder{

    //pointer pointing to the first node of MyStringBuilder Object.
    private CharacterNode firstNode;

    /**This method appends a char to the end of the MyStringBuilder
     *
     *@param addingChar char to add to MyStringBuilder 
     */
    public void append(char addingChar){

        //create a new CharacterNode object with parameter char
        CharacterNode newNode = new CharacterNode(addingChar);

        //if MyStringBuilder is empty, firstNode points to a new CharacterNode
        //containing this char
        if(firstNode == null){
            firstNode = newNode;
        }

        //set current node to first node
        CharacterNode currNode = firstNode;

        if(currNode.nextNode != null){
            //traverse through all nodes until arriving at the last node
            while(currNode.nextNode != null){  

                currNode = currNode.nextNode;
            }
        }
        //now currNode is the last node as it is pointing to null.
        //Let nextNode pointer of currNode point to the newly creatly node
        if(newNode != firstNode){
            currNode.nextNode = newNode;}


    }


    /**This method converts sequence of nodes of chars to String
     *
     *@return String with chars contained in CharacterNodes
     */
    public String toString(){

        //if it is a null string, return empty string
        if(firstNode == null){ return ""; } 

        //create a String to add char to
        String str = new String();

        //set current node as the first node to start 
        CharacterNode currNode = firstNode; 

        //trasverse through the sequence
        while(currNode.nextNode != null){

            char ch = currNode.getLetter();

            //concatinate character to the string
            str = str.concat(Character.toString(ch));

            //set current node to the next node
            currNode = currNode.nextNode;
        }
        //add the last node 
        char c = currNode.getLetter();
        str = str.concat(Character.toString(c));

        return str;
    }



    /**This method returns the number of nodes in CharacterNodes.
     *
     *@return number of nodes
     */
    public int length(){

        if(firstNode == null){ return 0;}

        //set currNode as the first node
        CharacterNode currNode = firstNode;

        //variable to store count
        int i=0;

        //count how many times currNode points to different objects
        while(currNode.nextNode != null){

            currNode = currNode.nextNode;

            i++;
        }
        //return count plus one since the last node was not counted in the loop
        return i+1;
    }



    /**This method inserts a char to the position offset.
     *
     *@param offset the position where char will be inserted
     *@param insertCharr char to insert to the certain position offset
     */
    public void insert(int offset, char insertChar) 
        throws IndexOutOfBoundsException{

            //if offset is negative or greater than length of the sequence, 
            //print error message
            if(offset < 0 || offset > this.length()){
                throw new IndexOutOfBoundsException("Offset is invalid"); }

            //create new CharacterNode for char insertChar
            CharacterNode newNode = new CharacterNode(insertChar);

            //if inserting at position one, let new node point to firstNode
            if(offset==0){ newNode.nextNode = firstNode;

                firstNode = newNode;
            }

            else if(offset != 0){   

                CharacterNode currNode = firstNode;

                System.out.println(this.toString());

                int i = 0;

                //loop until reaching a node before offset position
                while(i < offset-1){

                    currNode = currNode.nextNode;
                    i++;
                }

                //let newly created node's next node point to node at offset
                //positon
                newNode.nextNode = currNode.nextNode;

                //let a node before offset point to the newly created node
                currNode.nextNode = newNode; 
            }
        }

    /**This method deletes a subsequence of characters of specified range
     *
     * @param start position where deletion will be started
     * @param end position where deletion will end.position at end is exclusive
     */
    public void delete(int start, int end)
        throws StringIndexOutOfBoundsException{

            //if start position is greater than end position or string lenght 
            //or is less than zero, throw exception
            if(start<0 || start > end || start > this.length()){ throw new 
                StringIndexOutOfBoundsException("start position is invalid"); }

            //if end is greater than length, set it to the length of the string
            if(end > this.length()){ end = this.length(); }

            CharacterNode currNode = firstNode;

            int i=0;

            //loop through the sequence until reaching start position
            while(i < start-1){

                currNode = currNode.nextNode;
                i++;
            }
            //now currNode.next node is the char to be deleted
            //store the pointer to start node by another reference
            CharacterNode startNode = currNode;

            //to count how many char will be deleted
            int j=0;

            //loop until reaching the end position
            while(j < end-start){
                currNode = currNode.nextNode;
                j++; }

            //if deletion starts from the first char, let firstNode point to 
            //end position
            if(start==0){ firstNode = currNode; }

            //let the the node at start position point to the node at last 
            //position
            else if(start != 0){ 
                startNode.nextNode = currNode.nextNode;}

        }



    /**This method checks whether the calling object MyStringBuilder object and
     * other is equal to one another.
     *
     * @param other Object to compare calling object to
     *
     *@return true if two objects are same, false otherwise
     */
    public boolean equals(Object other){

        //if object is not MyStringBuilder object, return false
        if(!(other instanceof MyStringBuilder)){return false;}

        //checking for length
        if(this.length() != ((MyStringBuilder)other).length()){return false;}

        CharacterNode currNodeThis = this.firstNode;
        CharacterNode currNodeOther = ((MyStringBuilder)other).firstNode;

        //cheking contents
        for(int i=0; i<this.length(); i++){

            char cThis = currNodeThis.getLetter();
            char cOther = currNodeOther.getLetter();

            if(cThis != cOther){ return false; }

            currNodeThis = currNodeThis.nextNode;
            currNodeOther = currNodeOther.nextNode;

        }
        return true;
    }



    /**This method checks whether the calling object MyStringBuilder object and
     * String is equal to one another.
     *
     *@param other String to compare calling object to
     *
     *@return true if two objects are same, false otherwise
     */
    public boolean equals(String other){

        //check if the length of calling object is same as that of parameter 
        //string
        if(this.length() != ((String)other).length()){return false;}

        //set currNode as the first node of the calling object
        CharacterNode currNode =this.firstNode;

        int i = 0;
        //loop through the sequence, comparing char by char
        while(currNode.nextNode != null){

            char ch = currNode.getLetter();

            if(ch != other.charAt(i)){return false;}

            currNode = currNode.nextNode;

            i++;
        }
        //check char in last node and last char of the input string
        char c = currNode.getLetter();

        if(c != other.charAt(other.length()-1)){ return false; }

        return true;
    }



    //main for testing
    public static void main(String args[]){

        MyStringBuilder ms = new MyStringBuilder();

        ms.append('h');
        ms.append('e');
        ms.append('l');
        ms.append('l');
        ms.append('o');

        //now MyStringBuilder ms's sequence: hello

        System.out.println("testing append: should be 'hello' :"+ms.toString());

        ms.delete(0,2);

        System.out.println("testing delete: should be 'llo' : "+ms.toString());


        ms.insert(0, 'e');

        System.out.println("testing insert: should be 'ello': "+ms.toString());

        System.out.println("testing length: length for 'ello' shoudl be four: "
                +ms.length());

        MyStringBuilder ms1 = new MyStringBuilder();

        ms1.append('l');
        ms1.append('l');
        ms1.append('e');
        ms1.append('p');

        /*    int i = 0;
              while(i<200000){

              ms1.append('x');
              i++;
              }*/

        //now MyStinrgBUilder ms1's sequence is 'lleo'

        System.out.println("ms1 : " +ms1.toString());

        System.out.println("testing equals for MSB: should be true: "
                +ms.equals(ms1));

        String str = new String("llew");

        System.out.println("string is: " + str);

        System.out.println("testing equals for string: should be false: "
                +ms1.equals(str));
    }

}//end of class 
