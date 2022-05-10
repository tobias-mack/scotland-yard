package fileIOComponent


import com.google.inject.AbstractModule
import com.google.inject.name.Names
import net.codingwell.scalaguice.ScalaModule
import fileIOComponent.*

class FileIOModule extends AbstractModule:
  override def configure() =
    bind(classOf[FileIOInterface]).to(classOf[fileIO_JSON_Impl.FileIOJSON])