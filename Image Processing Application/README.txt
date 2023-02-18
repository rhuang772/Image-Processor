Description:

Interfaces
ImageProcessingController, the Controller Interface as typical, is solely responsible for parsing user inputs, and then passing them to other models. Therefore, this interface only contains the playGame() method which takes in user inputs through the console, and then passes those inputs to the required locations dependent on those inputs. 

ImageProcessingControllerImpl, implements the aforementioned interface, this class passes user inputs into the given model, and then controls when the view should render messages or images. For instance, the ImageProcessControllerImpl has the responsibility of reading the correct command, delegating to classes to create images, then passing in correct inputs to the model for it to do its operations, among other things. If any inputs are passed in the order we are not expecting, we ask the user to re-enter them in the correct order.

The ImageProcessingModel Interface contains the methods that will perform certain operations on a given image. This interface offers all the possible functions that the model can do on a given image, some of them include: greyScaling, Brightening/Darkening, Flipping Vertically and Horizontally.

ImageProcessingImpl: implements the method, and then contains the hashmap that contains the files, and then other functions to do the job properly. The hashmap contains key-value pairs that correspond with a String filename, the key, and a 2D board of colors, the value. We retrieve keys and values later to support other methods in other classes. 

ImageHelper this class serves as a way to change valid file types passed in, into 2D arrays for the sole purpose of assisting the model with its rendering of files. Within this class, the helper takes in the height, width, and the array of integers that it's working with, and then creates the associated colors in its proper arrangement.

ImageUtil this class serves to obtain necessary values of the file that other classes need to use. For instance, this class contains functions to get the Width/Height of a valid file associated with a file name passed into its methods, determines if the file type is a ppm(which will later add functionality for other files), and converting a given file into a readable arraylist that other classes can use.

ImageProcessingView contains the methods required for the controller to print messages out to the console, and then Write files with the given 2D array Image to the correct file type.

PPMViewImpl Implements the view and this class specifically has the purpose of translating 2D arrays of Colors to readable PPM file types



*Updated Classes/Additions -> Assignment 5
-Introduced a new Interface Command Controller, and alongside of it a package storing commands that implement this interface. We felt it was necessary to add this addition to our program since adding these classes allowed us to implement the command design pattern within our ImageProcessingControllerImpl class. 
This allows us to shrink the amount of code within our controller, making adding in extra functionality in our controller for later methods such as blurring/sharpening within this assignment more simple. Also reduces duplicate code/gives us better design for our switch statements.

-Alongside the new Interface, we added serval command classes: Brighten/Darken/Filter/GreyScale/Horizontal Flip/VerticalFlip/Load
Each of these classes carry out a specific function, of parsing specific inputs to the correct method within the model. For instance, the Brighten class uses the models implementation of makeBrighter using the passed in inputs.

-Changes within the controller
Within our controller Impl we made changes to our previous designs which featured massive switch statements filled with code to utilize the command design pattern we added implementation for. 

-Changes within ImageUtils
Within our Image Utils class we added functionality to process the new listed file formats into our desired image type while retaining functionality of ppms.

-Changes within the View
Within our View class we added functionality to convert 2D arrays of colors to new file formats, while retaining previous functionality of ppm formats.
Furthermore we moved image saving to be done by the class.

-Introduced a new Interface ImageFilter
This interface is stored within the same package as the model, and after being initialized, offers functionality for the new image processing methods: blur, sharpen, luma greyscale(with matrix operations), and sepiaTone.
-Alongside the interface, we added a class that implements the interface, which functions to implement these methods with the correct kernels/matrixes embedded within each function, and serve to produce the correct image as specified by the assignment.

-Introduced a new Interface ImageMatrix
This interface is also stored within the model, and serves to translate a given 2D array of colors into a Matrix to do the operations that we need within the assignment, in particular, matrix multiplication.
-Alongside the interface, we added a class that implements said interface, which functions to ()

-Small change within the model, we have removed the fact that load throws a NoSuchFileException, after further review, we found that this exception was not necessary and justly removed it from the method signature.

Parts that are complete
Within the assignment, all the listed requirements from Section 4 of the Assignment page are complete, that includes: Supporting the ability to load/save images in particular formats and switching between formats, Supporting the ability to blur and sharpen images, produce grayscale and sepia versions of images, Supporting the ability to accept a script file as a command line option, and if no script is provided, it becomes an interactive entry of script commands as before, and lastly, keeping the support for all previous operations and script commands.

Assignment 6 Updates
New Classes:
To support the new GUIs, we added in a new Controller - “GUIController” that supports the functionality for our GUI class.

To add support for our Controller we added in a new Interface- “Features” that helps to serve as a way for the controller to communicate with the view to properly display images, while maintaining our design principles of flexibility and extensibility.

To implement the GUI we added in a new class within the View -”GUI view” that extends a JFrame, that allows us to create our GUI, and supports the previous modifications of an uploaded image, while displaying the image.

Furthermore, for our GUI view, we created an interface- GUIInterface with the methods of addFeatures and seImageIcon, which allow for the implemented class to communicate with the controller and pass in modify and set the image that it is working in.

 For our attempt at the Histogram, we added in several new classes within our view that extend JPanels that helped to serve to draw the image onto the panel, which would then be combined to a total container panel that would then be presented and updated depending on the image currently worked on.

Modifications to Previous design
All previous classes remain unchanged except for our previous view, the only modification that we felt was necessary was to remove the file writing capabilities nested solely within that class, and move it our imageUtil class instead. We felt this change was necessary since after adding in our new GUI we realized that the ability to generate files should not need to be a single view function, furthermore to remove duplicate code, we did not want to copy the same features/method of file writing to a specific view, therefore we delegated this functionality for our ImageUtils and then removed the methods from our class/interface.

Parts that are Complete *
Within the Assignment the only part that is not complete within the assignment, is just the histogram. 

/************Although the image loads for initial images, modifications and loading in already modified images proceed to break the program, and generate null pointer exceptions within our calculations therefore we felt that we should not add it within our method. If you wish to view our implementation look for the commented out code within the view under the histoPanelInit() method within our GUIView implementation. ************/ 

Aside from that the other requirements for our GUI are complete and implemented: We used Java Swing to build our graphical interface, shows the current image being worked on, the interface exposes all the required features of the program(flips, component visualization, greyscale, blurring, sharpening, and sepia), saving supports PNG/PPM/JPG/BMP the user can suitably load in images, and error conditions are met with pop ups guiding the user how to correctly use the program. We also have support for the required command line options in the jar: text, -file (filepath), and no command line options, running the GUI outright.

Image citation: 
Image Citation: mordecai and rigby from shutterstock

Script (To just copy paste and run all commands)

load res/Frog.ppm frog
brighten 10 frog frog-brighten
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

To start the program, you do not need to pass in program arguments into main, simply just run the program through the given ImageMain class. Upon start, you will be prompted to enter a file, in the line below it type in the command load “*Destination of file* frog. Doing the operations are fairly simple here's a list of all supported commands, and then a reference fileName

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




