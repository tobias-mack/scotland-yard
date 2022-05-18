package fileIOComponent

import com.google.inject.AbstractModule
import com.google.inject.name.Names
import fileIOComponent.databaseComponent.{DBInterface,DAOInterface}
import fileIOComponent.databaseComponent.Slick.{DBSlickImpl,DAOSlickImpl}
import fileIOComponent.databaseComponent.MongoDB.MongoDBImpl
import fileIOComponent.*
import net.codingwell.scalaguice.ScalaModule

class PersistenceModule extends AbstractModule :
  override def configure() =
    bind(classOf[FileIOInterface]).to(classOf[fileIO_JSON_Impl.FileIOJSON])
    bind(classOf[DBInterface]).to(classOf[DBSlickImpl])
    bind(classOf[DAOInterface]).to(classOf[MongoDBImpl])
