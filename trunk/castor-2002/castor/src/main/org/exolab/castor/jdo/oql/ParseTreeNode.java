/**
 * Redistribution and use of this software and associated documentation
 * ("Software"), with or without modification, are permitted provided
 * that the following conditions are met:
 *
 * 1. Redistributions of source code must retain copyright
 *    statements and notices.  Redistributions must also contain a
 *    copy of this document.
 *
 * 2. Redistributions in binary form must reproduce the
 *    above copyright notice, this list of conditions and the
 *    following disclaimer in the documentation and/or other
 *    materials provided with the distribution.
 *
 * 3. The name "Exolab" must not be used to endorse or promote
 *    products derived from this Software without prior written
 *    permission of Exoffice Technologies.  For written permission,
 *    please contact info@exolab.org.
 *
 * 4. Products derived from this Software may not be called "Exolab"
 *    nor may "Exolab" appear in their names without prior written
 *    permission of Exoffice Technologies. Exolab is a registered
 *    trademark of Exoffice Technologies.
 *
 * 5. Due credit should be given to the Exolab Project
 *    (http://www.exolab.org/).
 *
 * THIS SOFTWARE IS PROVIDED BY EXOFFICE TECHNOLOGIES AND CONTRIBUTORS
 * ``AS IS'' AND ANY EXPRESSED OR IMPLIED WARRANTIES, INCLUDING, BUT
 * NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND
 * FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL
 * EXOFFICE TECHNOLOGIES OR ITS CONTRIBUTORS BE LIABLE FOR ANY DIRECT,
 * INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION)
 * HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT,
 * STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED
 * OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 * Copyright 1999 (C) Exoffice Technologies Inc. All Rights Reserved.
 *
 * $Id$
 */


package org.exolab.castor.jdo.oql;

import java.util.Vector;
import java.util.Enumeration;

/**
 * A Node in the Parse tree which is generated by the {@link Parser} as the 
 * tree representation of the OQL Query.  Each node has a link back to the 
 * parent node (null for the root node), and a vector of children.  Each node
 * contains the {@link Token} which represents that part of the tree.
 *
 * @author  <a href="nissim@nksystems.com">Nissim Karpenstein</a>
 * @version $Revision$ $Date$
 */
public class ParseTreeNode implements TokenTypes {
  
  //private members for tree structure
  private ParseTreeNode _parent;
  private Vector _children = new Vector();

  //private data members
  private Token _token;


  /**
   * Creates a new Node with supplied parent and token.
   *
   * @param parent The parent of this node (null for root)
   * @param token The token data in this node
   */
  public ParseTreeNode(ParseTreeNode parent, Token token) {
    _parent = parent;
    _token = token;
  }

  /**
   * Creates a new root Node with supplied token.
   *
   * @param token The token data in this node
   */
  public ParseTreeNode(Token token) {
    _parent = null;
    _token = token;
  }

  /**
   * Changes the parent of this node.
   *
   * @param parent The new parent.
   */
  public void setParent(ParseTreeNode parent) {
    _parent = parent;
  }

  /**
   * Adds a new node as a child of this node.  Changes the nodes parent to 
   * this.
   *
   * @param child The new child
   */
  public void addChild(ParseTreeNode child) {
    child.setParent(this);
    _children.addElement(child);
  }

  /**
   * Specifies whether this node is the root of a tree.
   *
   * @return True if the node does not have a parent, otherwise false.
   */
  public boolean isRoot() {
    return (_parent == null);
  }

  /**
   * Specifies whether this node is a leaf.
   *
   * @return True if the node does not have any children, otherwise false.
   */
  public boolean isLeaf() {
    return (_children.size() == 0);
  }

  /**
   * Accessor method for the parent of this node.
   *
   * @return The parent of this node.
   */
  public ParseTreeNode getParent() {
    return _parent;
  }

  /**
   * Accessor method for an enumeration of this nodes children.
   *
   * @return An Enumeration of children.
   */
  public Enumeration children() {
    return _children.elements();
  }

  /**
   * Accessor method for individual children of this node.
   *
   * @param index the index of the child to retrieve.
   * @return the index child of this node.
   */
  public ParseTreeNode getChild(int index) {
    return (ParseTreeNode) _children.get(index);
  }

  /**
   * Accessor method for the number of children of this node.
   *
   * @return the number of children of this node.
   */
  public int getChildCount() {
    return _children.size();
  }  

  /**
   * Accessor method for the token.
   *
   * @return The token which is the datum of this node.
   */
  public Token getToken() {
    return _token;
  }

}
