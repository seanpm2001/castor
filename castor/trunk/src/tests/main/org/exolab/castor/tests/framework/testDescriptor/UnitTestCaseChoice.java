/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.0.3</a>, using an XML
 * Schema.
 * $Id$
 */

package org.exolab.castor.tests.framework.testDescriptor;

  //---------------------------------/
 //- Imported classes and packages -/
//---------------------------------/

import org.exolab.castor.xml.Marshaller;
import org.exolab.castor.xml.Unmarshaller;

/**
 * Class UnitTestCaseChoice.
 * 
 * @version $Revision$ $Date$
 */
public class UnitTestCaseChoice implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _mapping_File
     */
    private java.lang.String _mapping_File;

    /**
     * Field _schema
     */
    private java.lang.String _schema;


      //----------------/
     //- Constructors -/
    //----------------/

    public UnitTestCaseChoice() 
     {
        super();
    } //-- org.exolab.castor.tests.framework.testDescriptor.UnitTestCaseChoice()


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Returns the value of field 'mapping_File'.
     * 
     * @return the value of field 'Mapping_File'.
     */
    public java.lang.String getMapping_File()
    {
        return this._mapping_File;
    } //-- java.lang.String getMapping_File() 

    /**
     * Returns the value of field 'schema'.
     * 
     * @return the value of field 'Schema'.
     */
    public java.lang.String getSchema()
    {
        return this._schema;
    } //-- java.lang.String getSchema() 

    /**
     * Method isValid
     * 
     * 
     * 
     * @return true if this object is valid according to the schema
     */
    public boolean isValid()
    {
        try {
            validate();
        }
        catch (org.exolab.castor.xml.ValidationException vex) {
            return false;
        }
        return true;
    } //-- boolean isValid() 

    /**
     * 
     * 
     * @param out
     * @throws org.exolab.castor.xml.MarshalException if object is
     * null or if any SAXException is thrown during marshaling
     * @throws org.exolab.castor.xml.ValidationException if this
     * object is an invalid instance according to the schema
     */
    public void marshal(java.io.Writer out)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        
        Marshaller.marshal(this, out);
    } //-- void marshal(java.io.Writer) 

    /**
     * 
     * 
     * @param handler
     * @throws java.io.IOException if an IOException occurs during
     * marshaling
     * @throws org.exolab.castor.xml.ValidationException if this
     * object is an invalid instance according to the schema
     * @throws org.exolab.castor.xml.MarshalException if object is
     * null or if any SAXException is thrown during marshaling
     */
    public void marshal(org.xml.sax.ContentHandler handler)
        throws java.io.IOException, org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        
        Marshaller.marshal(this, handler);
    } //-- void marshal(org.xml.sax.ContentHandler) 

    /**
     * Sets the value of field 'mapping_File'.
     * 
     * @param mapping_File the value of field 'mapping_File'.
     */
    public void setMapping_File(java.lang.String mapping_File)
    {
        this._mapping_File = mapping_File;
    } //-- void setMapping_File(java.lang.String) 

    /**
     * Sets the value of field 'schema'.
     * 
     * @param schema the value of field 'schema'.
     */
    public void setSchema(java.lang.String schema)
    {
        this._schema = schema;
    } //-- void setSchema(java.lang.String) 

    /**
     * Method unmarshal
     * 
     * 
     * 
     * @param reader
     * @throws org.exolab.castor.xml.MarshalException if object is
     * null or if any SAXException is thrown during marshaling
     * @throws org.exolab.castor.xml.ValidationException if this
     * object is an invalid instance according to the schema
     * @return the unmarshaled
     * org.exolab.castor.tests.framework.testDescriptor.UnitTestCaseChoice
     */
    public static org.exolab.castor.tests.framework.testDescriptor.UnitTestCaseChoice unmarshal(java.io.Reader reader)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        return (org.exolab.castor.tests.framework.testDescriptor.UnitTestCaseChoice) Unmarshaller.unmarshal(org.exolab.castor.tests.framework.testDescriptor.UnitTestCaseChoice.class, reader);
    } //-- org.exolab.castor.tests.framework.testDescriptor.UnitTestCaseChoice unmarshal(java.io.Reader) 

    /**
     * 
     * 
     * @throws org.exolab.castor.xml.ValidationException if this
     * object is an invalid instance according to the schema
     */
    public void validate()
        throws org.exolab.castor.xml.ValidationException
    {
        org.exolab.castor.xml.Validator validator = new org.exolab.castor.xml.Validator();
        validator.validate(this);
    } //-- void validate() 

}
