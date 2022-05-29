package fileIOComponent.databaseComponent

import com.google.inject.Inject

class GameDataDefaultPrototype extends GameDataPrototype:
  override def cloneGameData() =
    this