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
 *    permission of Intalio, Inc.  For written permission,
 *    please contact info@exolab.org.
 *
 * 4. Products derived from this Software may not be called "Exolab"
 *    nor may "Exolab" appear in their names without prior written
 *    permission of Intalio, Inc. Exolab is a registered
 *    trademark of Intalio, Inc.
 *
 * 5. Due credit should be given to the Exolab Project
 *    (http://www.exolab.org/).
 *
 * THIS SOFTWARE IS PROVIDED BY INTALIO, INC. AND CONTRIBUTORS
 * ``AS IS'' AND ANY EXPRESSED OR IMPLIED WARRANTIES, INCLUDING, BUT
 * NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND
 * FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL
 * INTALIO, INC. OR ITS CONTRIBUTORS BE LIABLE FOR ANY DIRECT,
 * INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION)
 * HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT,
 * STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED
 * OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 * Copyright 2001 (C) Intalio, Inc. All Rights Reserved.
 *
 * $Id $
 */
package org.exolab.castor.persist;

/**
 * An Entity represents a tuple of values in a data store.
 * <p>
 * Each entity belongs to some entity classes. Entity class is a type structure
 * that base on a single inheritance model. Each entity class may have zero or 
 * more sub entity class(es), and each sub entity class may have zero or one
 * super entity classes. If an entitiy belongs to a sub entity class, it also 
 * belong to the super entity class and all its super entity classes, but not
 * the other way around. The ultimate super entity class is called a base 
 * entity class.
 * <p>
 * The EntityInfo contains the type information for a base class and all its
 * directy or indirect sub classes. Each sub class EntityInfo describes the 
 * field in additional to the super class EntityInfo and is not repeated and 
 * cannot be overrided, except if it is a identity. In most case, the actual 
 * entity doesn't span all the classes described in the EntityInfo.
 * <p>
 * An entity can be distinguished from any other entity with the same base by 
 * an identity. An identity can be one of the value, or composed by multiple 
 * values in an entity. Multiple values identity is wrapped in 
 * {@link org.exolab.castor.persist.types.Complex}.
 * <p>
 * An entity may has a stamp. A stamp is a value in an entity that can be used to 
 * identify the state of an entity. The state of entity is change in every update, 
 * and a stamp is a value in the entity that always change when the state of the 
 * entity change.
 * <p>
 * For example, the time of the last modification on an entity can be used as a 
 * stamp. A sequence counter that increment itself after each modification to the 
 * entity can also be used as a stamp.
 */
public final class Entity implements Cloneable {

    /**
     * The base type of the entity.
     */
    public EntityInfo   base;

    /**
     * The identity of this entity.
     */
    public Object       identity;

    /**
     * The timestamp or counter of the entity in long.
     */
    public long         longStamp;

    /**
     * The timestamp or counter of the entity in object type.
     */
    public Object       objectStamp;

    /**
     * Indicate this entity is locked in the data store.
     */
    public boolean      locked;

    /** 
     * Indicate which entity classes is this entity actually belongs to
     * The super-most entity of "actual" must equal to "root".
     */ 
    public EntityInfo   actual;

    /**
     * Represent the actual values in the data store. The values is
     * ordered in "super-most first" order. In the other words, the value 
     * representing the root.fieldInfo[0] is stored in position zero of 
     * values. Assuming actual is an immediate sub entity of root, the
     * first values of actual is stored in value[root.fieldInfo.lenght].
     */
    public Object[]     values;

    /**
     * Constructor
     */
    public Entity() {
        super();
    }

    /**
     * Constructor
     */ 
    public Entity( EntityInfo base, Object identity ) {
        super();
        this.base = base;
        this.identity = identity;
    }

    /**
     * Constructor
     */
    public Entity( EntityInfo base, EntityInfo actual, Object identity ) {
        super();
        this.base = base;
        this.actual = actual;
        this.identity = identity;
    }

    /**
     * Test if this object belongs to the same entity type and has the 
     * same identity. (warning! this behavior subject to change)
     *
     * @specify by java.lang.Object.equals(Object)
     */
    public boolean equals(Object obj) {
        Entity ent;

        if (obj == null || !(obj instanceof Entity)) {
            return false;
        }
        ent = (Entity) obj;
        return (base.equals(ent.base) && identity.equals(ent.identity));
    }

    /**
     * Return the hashCode of this entity
     *
     * @specify by java.lang.Object.hashCode()
     */
    public int hashCode() {
        return base.hashCode() + (identity == null ? 0 : identity.hashCode());
    }

    /**
     * Get a string representing this entity
     * 
     * @return a string representing this entity
     */
    public String toString() {
        return base + "[" + identity + "]";
    }

    /**
     * Get a clone of this object
     *
     * @return an new instance of the same object that has the same values
     */
    public Object clone() throws CloneNotSupportedException {

        Entity target = (Entity) super.clone();
        target.actual = actual;
        if ( values != null ) {
            target.values = new Object[values.length];
            System.arraycopy( values, 0, target.values, 0, values.length );
        } else {
            target.values = null;
        }
        return target;
    }

    /**
     * Copy all the fields of this entity into the target entity
     *
     * @param target The entity that the fields to be copied into
     */
    public void copyInto( Entity target ) {

        target.base        = base;
        target.identity    = identity;
        target.longStamp   = longStamp;
        target.objectStamp = objectStamp;
        target.locked      = locked;
        target.actual      = actual;
        if ( values != null ) {
            target.values = new Object[values.length];
            System.arraycopy( values, 0, target.values, 0, values.length );
        } else {
            target.values = null;
        }
    }

    /**
     * Obtain the value of a field represented by fieldInfo, if this entity
     * is initalized. FieldInfo must belongs to one of the entity that is lay 
     * in the path between root and actual.
     * <p>
     * If this entity is not initalized, it method always return false
     * <p>
     */
    public Object getFieldValue( EntityFieldInfo fieldInfo ) 
            throws IllegalArgumentException {

        if ( base == null || actual == null || values == null )
            return null;

        // make sure fieldInfo is in the path between actual and root
        if ( !fieldInfo.entityClass.isSuper( actual ) )
            throw new IllegalArgumentException("Entity " + fieldInfo.entityClass 
                      + " for the field " + fieldInfo + " not found in " + this);

        // get the field
        int pos  = fieldInfo.entityClass.getFieldOffset();
            pos += fieldInfo.getFieldPos();
        return values[pos];
    }

    /**
     * Set the value of the field represents by the specified fieldInfo
     * to be the specified value.
     * <p>
     * If this entity is uninitialized, this method will initalized it.
     * <p>
     * This method throws IllegalArgumentException if the fieldInfo 
     * is not compatible with the existing state. For example, fieldInfo
     * does not belong to the path between {@link #actual} and {@link #base}
     */
    public void setFieldValue( EntityFieldInfo fieldInfo, Object value ) 
            throws IllegalArgumentException {

        // check the state of root
        if ( base == null )
            // set root if it is not already been set
            base = fieldInfo.entityClass.getBase();
        else if ( !fieldInfo.entityClass.getBase().equals( base ) ) 
            throw new IllegalArgumentException("EntityFieldInfo does not contains the same root");

        // check the state of actual
        if ( actual == null ) {
            // set actual if not already been set
            actual = fieldInfo.entityClass;
        } else if ( !fieldInfo.entityClass.isSuper( actual ) ) {
            // make sure the new field is compatible with the path between
            // rooto and actual
            if ( actual.isSuper( fieldInfo.entityClass ) )
                // upgrade the entity to a sub entity
                actual = fieldInfo.entityClass;
            else
                throw new IllegalStateException("Field represented by "+fieldInfo+" is not compatible");
        }

        // check the state of value
        if ( values==null ) {
            values = new Object[base.getMaxLength()];
        } else if ( values.length < (actual.getFieldOffset()+actual.fieldInfo.length) ) {
            Object[] old = values;
            values = new Object[base.getMaxLength()];
            System.arraycopy( old, 0, values, 0, old.length );
        }

        int pos  = fieldInfo.entityClass.getFieldOffset();
            pos += fieldInfo.getFieldPos();
        values[pos] = value;
    }

}
