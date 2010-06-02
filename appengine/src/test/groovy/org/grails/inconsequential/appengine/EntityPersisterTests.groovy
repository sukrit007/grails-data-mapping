package org.grails.inconsequential.appengine

import com.google.appengine.api.datastore.Key
import org.grails.inconsequential.core.DatastoreContext
import org.grails.inconsequential.appengine.testsupport.AppEngineDatastoreTestCase

/**
 * @author Graeme Rocher
 * @since 1.1
 */
class EntityPersisterTests extends AppEngineDatastoreTestCase {



  void testPersistObject() {
    AppEngineDatastore ds = new AppEngineDatastore()
    ds.getMappingContext().addPersistentEntity(TestEntity)

    TestEntity t = new TestEntity()
    t.name = "bob"
    def conn = ds.connect(null)
    def ctx = new AppEngineContext(conn, ds.mappingContext)
    ds.persist(ctx, t)

    assert t.id != null

    t = ds.retrieve(ctx, TestEntity, new AppEngineKey(t.id))

    assert t != null
    assert "bob"  == t.name

  }
}
class TestEntity {
  Key id
  String name
}
