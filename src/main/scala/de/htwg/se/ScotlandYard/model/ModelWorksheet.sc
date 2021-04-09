case class Cell(x:Int, y:Int)

val cell1 = Cell(4,5)
cell1.x
cell1.y

case class Field(cells: Array[Cell])

val field1 = Field(Array.ofDim[Cell](1))
field1.cells(0)=cell1
field1.cells(0).x
field1.cells(0).y
//comment
print("Scotland" + "Yard")
var a = 5
val b = 9   //immutable
var c = 2
class Person(var name: String, var age: Int){
  def isOld: Boolean = age>60
}

val p = new Person("Albert",89)
print(p.name + " is " + p.age + " years old.")
if(p.isOld){
  print(p.name + " is old")
}
else{
  print(p.name + " is not old")
}
