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


package org.exolab.castor.jdo.engine;


import org.exolab.castor.mapping.MappingException;
import org.exolab.castor.mapping.ClassDesc;
import org.exolab.castor.mapping.FieldDesc;
import org.exolab.castor.mapping.ContainerFieldDesc;


/**
 * JDO class descriptors. Extends {@link ClassDesc} to include the
 * table name and other SQL-related information. All fields are of
 * type {@link JDOFieldDesc}, identity field is not included in the
 * returned field list, and contained fields are flattened out for
 * efficiency (thus all fields are directly accessible).
 *
 * @author <a href="arkin@exoffice.com">Assaf Arkin</a>
 * @version $Revision$ $Date$
 */
public class JDOClassDesc
    extends ClassDesc
{


    /**
     * True if any of the fields is marked for dirty checking.
     */
    private boolean _dirtyCheck;


    /**
     * The name of the SQL table.
     */
    private String  _tableName;


    /**
     * All the related tables/classes.
     */
    private JDORelationDesc[]  _related;


    /**
     * Constructs a new JDO descriptor. The field list must consist only of
     * JDO field, must not include the identity field, all contained fields
     * must be represented as {@link JDOContainedFieldDesc}. Order of fields
     * is not important. The identity field must be of type {@link JDOFieldDesc}
     * or {@link ContainerFieldDesc}.
     * 
     * @param javaClass The Java type of this class
     * @param tableName The SQL table name
     * @param fields The fields described for this class
     * @param identity The field of the identity (key) of this class,
     *   may be null
     * @param extend The descriptor of the class which this class extends,
     * or null if this is a top-level class
     * @param related Describes all relations
     * @throws MappingException Invalid mapping information
     */
    public JDOClassDesc( Class javaClass, String tableName, JDOFieldDesc[] fields,
                         FieldDesc identity, JDORelationDesc[] related, JDOClassDesc extend )
        throws MappingException
    {
        super( javaClass, fields, identity, extend );
        if ( tableName == null )
            throw new IllegalArgumentException( "Argument 'tableName' is null" );
        _tableName = tableName;
        if ( identity == null )
            throw new MappingException( "mapping.noIdentity", javaClass.getName() );
        if ( ! ( identity instanceof JDOFieldDesc ) &&
             ! ( identity instanceof ContainerFieldDesc ) )
            throw new IllegalArgumentException( "Identity field must be of type JDOFieldDesc or ContainerFieldDesc" );
        for ( int i = 0 ; i < fields.length ; ++i ) {
            if ( fields[ i ].isDirtyCheck() )
                _dirtyCheck = true;
        }
        _related = related;
    }


    /**
     * Constructor used by derived classes.
     */
    protected JDOClassDesc( JDOClassDesc clsDesc )
        throws MappingException
    {
        super( clsDesc.getJavaClass(), clsDesc.getFields(), clsDesc.getIdentity(), clsDesc.getExtends() );
        _tableName = clsDesc._tableName;
        _dirtyCheck = clsDesc._dirtyCheck;
        _related = clsDesc._related;
    }


    /*
    public JDOClassDesc( Class objType, String tableName, JDOFieldDesc[] fields,
			  PrimaryKeyDesc primKey, JDOFieldDesc keyField,
			  JDOClassDesc extend, RelationDesc[] related )
	throws MappingException
    {
	super( objType, fields, keyField, extend );
	for ( int i = 0 ; i < fields.length ; ++i ) {
	    if ( fields[ i ].isDirtyCheck() )
		_dirtyCheck = true;
	}
	_primKey = primKey;
	_related = ( related == null ? new RelationDesc[ 0 ] : related );
	_tableName = tableName;
    }


    protected JDOClassDesc( JDOClassDesc source, FieldDesc parentField,
			     FieldDesc parentRefField, String foreRef )
	throws MappingException
    {
	this( source.getJavaClass(), source.getSQLName(),
	      toContained( source.getJDOFields(), parentField, parentRefField ),
	      source.getPrimaryKey(), new JDOContainedFieldDesc( source.getPrimaryKeyField(), parentField, parentRefField ),
	      (JDOClassDesc) source.getExtends(), source.getRelated() );
	
	JDOFieldDesc[] descs;
	descs = source.getPrimaryKey().getJDOFields();
	if ( descs != null ) {
             for ( int i = 0 ; i < descs.length ; ++i )
                descs[ i ] = new JDOContainedFieldDesc( descs[ i ], parentField, parentRefField );
            _primKey = new PrimaryKeyDesc( source.getPrimaryKey().getPrimaryKeyType(), descs );
        } else {
            _primKey = source.getPrimaryKey();
        }
	for ( int i = 0 ; i < _fields.length ; ++i ) {
	    if ( ( (JDOFieldDesc) _fields[ i ] ).isDirtyCheck() )
		_dirtyCheck = true;
	}
	_related = source.getRelated();
	_tableName = source._tableName;
	//_foreRef = foreRef;
    }



    private static JDOFieldDesc[] toContained( JDOFieldDesc[] fields, FieldDesc parentField,
					       FieldDesc parentRefField )
	throws MappingException
    {
	for ( int i = 0 ; i < fields.length ; ++i ) {
	    fields[ i ] = new JDOContainedFieldDesc( fields[ i ], parentField, parentRefField );
	}
	return fields;

    }
    */


    /**
     * Returns true if at least one field requires dirty checking.
     *
     * @return True if at least one field requires dirty checking
     */
    public boolean isDirtyCheck()
    {
        return _dirtyCheck;
    }


    /**
     * Returns the table name to which this object maps.
     *
     * @return Table name
     */
    public String getTableName()
    {
        return _tableName;
    }


    /**
     * Returns the relations (tables/classes).
     *
     * @return Relations, may be null
     */
    public JDORelationDesc[] getRelations()
    {
        return _related;
    }


    /*
    public void copyInto( Object source, Object target )
    {
	super.copyInto( source, target );
        for ( int i = 0 ; i < _related.length ; ++i ) {
            if ( _related[ i ].getParentField().getValue( source ) == null ) {
                Object relTarget;
 
                relTarget = _related[ i ].getParentField().getValue( target );
                if ( relTarget != null ) {
                    _related[ i ].getPrimaryKeyField().setValue( relTarget, null );
                }
                _related[ i ].getParentField().setValue( target, null );
            } else {
                _related[ i ].copyInto( source, target );
                if ( _related[ i ].getRelationType() == Relation.OneToOne ) {
                    _related[ i ].getPrimaryKeyField().setValue( target, target );
                }
            }
        }
    } 
    */


    public String toString()
    {
        return super.toString() + " AS " + _tableName;
    }


}


