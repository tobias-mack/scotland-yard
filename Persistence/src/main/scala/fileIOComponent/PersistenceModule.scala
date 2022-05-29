package fileIOComponent

import com.google.inject.AbstractModule
import com.google.inject.name.Names
import fileIOComponent.*
import fileIOComponent.databaseComponent.DBInterface
import fileIOComponent.databaseComponent.MongoDB.MongoDBImpl
import fileIOComponent.databaseComponent.Slick.DBSlickImpl
import net.codingwell.scalaguice.ScalaModule

class PersistenceModule extends AbstractModule :
  override def configure() =
    bind(classOf[FileIOInterface]).to(classOf[fileIO_JSON_Impl.FileIOJSON])
    bind(classOf[DBInterface]).to(classOf[DBSlickImpl])
    //bind(classOf[DBInterface]).to(classOf[MongoDBImpl])
