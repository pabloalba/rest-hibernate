package hibernate.example

//tag::imports[]
import grails.test.mixin.TestFor
import grails.test.hibernate.*
//end::imports[]

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
//tag::spec[]
//tag::mongoSpec[]
@TestFor(Product)
class ProductSpec extends HibernateSpec {
//end::mongoSpec[]

    //tag::testName[]
    void "test domain class validation"() {
    //end::testName[]
        //tag::testInvalid[]
        when:"A domain class is saved with invalid data"
        Product product = new Product(name: "", price: -2.0d)
        product.save()

        then:"There were errors and the data was not saved"
        product.hasErrors()
        product.errors.getFieldError('price')
        product.errors.getFieldError('name')
        Product.count() == 0
        //end::testInvalid[]

        //tag::testValid[]
        when:"A valid domain is saved"
        product.name = 'Banana'
        product.price = 2.15d
        product.save()

        then:"The product was saved successfully"
        Product.count() == 1
        Product.first().price == 2.15d
        //end::testValid[]
    }
    
}
//end::spec[]