# Stamp & seals editor

A desktop application designed for creating digital sketches of stamps and seals. It provides an intuitive interface for
users to design custom stamps using graphical elements such as frames and text. Users can start a new project, add
graphical elements, modify their properties through a simple panel, and preview the design in real time. The project can
be saved for further editing or exported as an image.

## Usage

1. Download a release from the Releases tab.
2. Extract the program archive.
3. Launch the `bin\stamp-editor.bat` or `bin/stamp-editor` in the extracted archive.

## Building

Make sure you have JDK 21 and Maven installed and run `mvn clean javafx:jlink`. The runtime image will be located in the
`target` directory.
