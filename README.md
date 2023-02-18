# Image-Processor
A GUI application that allows for image processing and editing


Script (To just copy paste and run all commands)

load res/Frog.ppm frog
brighten 10 frog frog-brighten\n
darken 10 frog frog-darken
horizontal-flip frog frog-Horiz
vertical-flip frog frog-Vert
greyscale r frog frog-Red
greyscale b frog frog-Blue
greyscale g frog frog-Green
greyscale luma frog frog-Luma
greyscale val frog frog-val
greyscale intens frog frog-Intense
filter blur frog frog-blur
filter sharpen frog frog-sharp
filter luma frog frog-luma-2
filter sepia frog frog-sepia
save res/frog-Intense.ppm frog-Intense

To start the program, you do not need to pass in program arguments into main, simply just run the program through the given ImageMain class. Upon start, you will be prompted to enter a file, in the line below it type in the command load “*Destination of file* frog. Doing the operations are fairly simple here's a list of all supported commands, and then a reference fileName. Here, Frog.ppm is utilized as an example test file:

#load the image
load res/Frog.ppm frog

#brighten by a factor(in this instance 10)
brighten 10 frog frog-brighten

#darken by a factor(in this instance 10)
darken 10 frog frog-darken

#horizontally flip image
horizontal-flip frog frog-Horiz

#vertically flip image
vertical-flip frog frog-Vert

#grayscale image red
greyscale r frog frog-Red

#grayscale image blue
greyscale b frog frog-Blue

#grayscale image green
greyscale g frog frog-Green

#grayscale image luma
greyscale luma frog frog-Luma

#grayscale image value-component
greyscale val frog frog-val

#grayscale image Intensity
greyscale intens frog frog-Intense

#Saving a created image (frog-Intense)
save res/frog-Intense.ppm frog-Intense





