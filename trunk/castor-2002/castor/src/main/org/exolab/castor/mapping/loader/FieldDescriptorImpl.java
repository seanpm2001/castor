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


package org.exolab.castor.mapping.loader;


import org.exolab.castor.mapping.FieldDescriptor;
import org.exolab.castor.mapping.FieldHandler;
import org.exolab.castor.mapping.MappingException;


/**
 * A basic field descriptor implementation. Engines will extend this
 * class to provide additional functionality.
 *
 * @author <a href="arkin@exoffice.com">Assaf Arkin</a>
 * @version $Revision$ $Date$
 * @see ClassDesc
 */
public class FieldDescriptorImpl
    implements FieldDescriptor
{


    /**
     * The field handler for get/set field value.
     */
    private FieldHandler  _handler;


    /**
     * The name of this field in the object. The field must have a
     * name, even if it set through accessor methods.
     */
    private String        _fieldName;


    /**
     * The field type.
     */
    private Class         _fieldType;


    /**
     * True if the field is transient and should not be saved/stored.
     */
    private boolean       _transient;


    /**
     * True if the field type is immutable.
     */
    private boolean       _immutable;


    /**
     * True if the field type is immutable.
     */
    private boolean       _required;


    /**
     * Constructs a new field descriptor.
     *
     * @param fieldName The field name
     * @param typeInfo The field type information
     * @param handler The field handler (may be null)
     * @param trans True if the field is transient
     */
    public FieldDescriptorImpl( String fieldName, TypeInfo typeInfo, FieldHandler handler, boolean trans )
    {
        if ( fieldName == null )
            throw new IllegalArgumentException( "Argument 'fieldName' is null" );
        _fieldName = fieldName;
        _handler = handler;
        _immutable = typeInfo.isImmutable();
        _fieldType = typeInfo.getFieldType();
        _required = typeInfo.isRequired();
        _transient = trans;
    }


    /**
     * Constructor used by derived clases.
     */
    protected FieldDescriptorImpl( FieldDescriptorImpl fieldDesc )
    {
        this._handler = fieldDesc._handler;
        this._fieldName = fieldDesc._fieldName;
        this._fieldType = fieldDesc._fieldType;
        this._transient = fieldDesc._transient;
        this._immutable = fieldDesc._immutable;
        this._required = fieldDesc._required;
    }


    public String getFieldName()
    {
        return _fieldName;
    }


    public Class getFieldType()
    {
        return _fieldType;
    }


    public boolean isTransient()
    {
        return _transient;
    }


    public boolean isImmutable()
    {
        return _immutable;
    }


    public boolean isRequired()
    {
        return _required;
    }


    public FieldHandler getHandler()
    {
        return _handler;
    }


    /**
     * Mutator method used by {@link MappingLoader}.
     */
    void setHandler( FieldHandler handler )
    {
        _handler = handler;
    }


    public String toString()
    {
        return _fieldName + "(" + _fieldType.getName() + ")";
    }
    

}

