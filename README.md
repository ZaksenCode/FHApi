# FHApi
Floating Holograms Api (FHA) is a simple hologram api based on id system!
Every hologram created with this api has it own id and you can use it id to interact with hologram.
# Commands
/holo spawn 'text/item/block' 'id' - spawn new hologram. you can use 'last' or number in id argument to select id.

/holo remove 'id' - remove hologram.

/holo modify 'id' 'subcommand' - a meaningful command - it allows you to modify hologram
# Modify subcommands
/holo modify 'id' alignment 'left/center/rigth' - setup text align

/holo modify 'id' background 'red' 'green' 'blue' 'aplha' - setup backgrond color by ARGB

/holo modify 'id' billboard 'center/fixed/horizontal/vertical' - setup text follow option - just try it

/holo modify 'id' glow_color 'red' 'green' 'blue' - setup glow_color_ovveride for hologram by RGB

/holo modify 'id' rotation 'x' 'y' - setup total text rotation

/holo modify 'id' left_rotation '0' '0' '0' '1.0' - setup left text rotation by quaternion > better use rotation

/holo modify 'id' right_rotation '0' '0' '0' '1.0' - setup right text rotation by quaternion > better use rotation

/holo modify 'id' line_width 'width' - setup one line width for hologram. p.s. if text bigger than one line he wrap to next line

/holo modify 'id' position 'x' 'y' 'z' - setup hologram position

/holo modify 'id' scale '1.0' '1.0' '1.0' - setup hologram scale by Vector3f

/holo modify 'id' see_through 'true/false' - setup text 'see through' feature

/holo modify 'id' shadow 'true/false' - setup text shadow

/holo modify 'id' text 'text' - setup hologram text p.s. number of argument - unlimited

/holo modify 'id' text_opacity 'byte' - setup text opacity

/holo modify 'id' translation 'x' 'y' 'z' - setup text offset of hologram

/holo modify 'id' view_range '1.0' - setup hologram view range

/holo modify 'id' item 'item' - setup hologram item

/holo modify 'id' block 'block' - setup hologram block 
# Connect in project
## 1 step - Add the JitPack repository to your build file
```
	<repositories>
		<repository>
		    <id>jitpack.io</id>
		    <url>https://jitpack.io</url>
		</repository>
	</repositories>
```
## 2 step - Add the dependency
```
	<dependency>
	    <groupId>com.github.ZaksenCode</groupId>
	    <artifactId>FHApi</artifactId>
	    <version>0.4</version>
	</dependency>
```
# How use it
## To use it, you need 'Hologram' object, you can get it by:
1. use Holos.getHolo(int id):
```
IHologram newHolo = Holos.getHolo(1);
```
2. use Holos.spawnText(Location location) or Holos.spawnTextWithId(Location location, int id):
```
TextHologram holo = Holos.spawnText(new Location(world, 0, 100, 0));
TextHologram anotherHolo = Holos.spawnTextWithId(new Location(world, 0, 100, 0), 1);
```
> if you try summon hologram by Holos.spawnTextWithId(int id) - the result can be null, if hologram with that id already exists

## To change hologram data, you can use:
```
TextHologram tHolo = Holos.spawnText(new Location(world, 0, 100, 0));
ItemHologram iHolo = Holos.spawnItem(new Location(world, 0, 100, 0));
BlockHologram bHolo = Holos.spawnBlock(new Location(world, 0, 100, 0));
HoloText.setText(tHolo, "text");
HoloItem.setItem(iHolo, new ItemStack(Material.BARRIER));
HoloBlock.setBlock(bHolo, Material.BEDROCK.createBlockData());
```
