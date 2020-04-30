package cse12pa2student;

class Node {
	String value;
	Node next;

	public Node(String value, Node next) {
		this.value = value;
		this.next = next;
	}
}

public class LinkedSL implements StringList {
	Node front;
	int size;

	public LinkedSL(String[] contents) {

		this.size = contents.length;

		this.front = new Node(null, null);
		Node end = this.front;

		/*
		 * iterate through parameter ArrayList to create new node for each value in the
		 * ArrayList
		 */
		for (int i = 0; i < this.size; i++) {

			end.next = new Node(contents[i], null);
			end = end.next;
		}

		end.next = null;

	}

	public String[] toArray() {

		if (this.front == null) {
			return null;
		}

		String[] newList = new String[this.size];

		if (this.isEmpty() == false) {

			for (int i = 0; i < this.size; i++) {

				newList[i] = this.front.next.value;
				front = this.front.next;
			}
		}

		return newList;
	}

	public boolean isEmpty() {

		if (this.front.next == null) {
			return true;
		}

		return false;
	}

	public void transformAll(StringTransformer transformer) {

		Node currNode = this.front;

		for (int i = 0; i < this.size; i++) {

			// transform value of a node at index i and save it
			String ss = transformer.transformElement(currNode.next.value);

			currNode.next.value = ss;

			currNode = currNode.next;

		}

	}

	public void chooseAll(StringChooser sl) {

		if (front.next == null) {
			return;
		}

		// set currNode to point to the front node
		Node currNode = this.front;

		int initialSize = this.size;

		for (int i = 0; i < initialSize; i++) {

			if (currNode.next == null) {
				return;

			}
			String word = currNode.next.value;

			// when first node gets deleted
			if (false == sl.chooseElement(word) && word == this.front.next.value) {

				// set front node to point to 2nd item
				this.front.next = this.front.next.next;

				this.size = this.size - 1;

				continue;
			}

			// when node is being kept
			if (true == sl.chooseElement(word)) {

				currNode = currNode.next;

				continue;
			}

			// when node in the middle gets deleted
			if (false == sl.chooseElement(word)) {

				if (currNode.next.next != null && word != this.front.next.value) {

					currNode.next = currNode.next.next;

					this.size = this.size - 1;

					continue;
				}
			}

			// when last node gets deleted
			if (false == sl.chooseElement(word) && currNode.next.next == null) {

				// set the second to last node to point to null
				currNode.next = null;

				this.size = this.size - 1;

				continue;

			}
		}

	}

}
