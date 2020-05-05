/**Soyeon Kim
 *3/8/18
 *
 *This file contains a CharacterNode class.
 */

/**This class CharacterNode object contais a char and an instance variable 
 * that points to another CharacterNode.
 */
public class CharacterNode{

    private final char letter;

    //CharacterNode pointer 
    public CharacterNode nextNode;

    /**General constructor for CharacterNode
     *
     *@param char to be stored
     */
    public CharacterNode(char ch){

        letter = ch;
    }

    /**Getter for char stored in a CharacterNode object
     *
     *@return char in the calling object
     */
    public char getLetter(){
        return letter;
    }

}
