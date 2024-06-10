# FontIdentifier_JAVA

This project contains Java code for identifying the font used in a picture. It uses the Tesseract OCR library to extract text from an image and the FontMatcher API to identify the font.

## Prerequisites

1. **Java Development Kit (JDK)**: Ensure you have JDK installed.
2. **Tesseract OCR**:
   - Download and install Tesseract from [here](https://github.com/tesseract-ocr/tesseract).
   - Add Tesseract executable to your system's PATH.
3. **Maven**: For managing dependencies.

## Setup

1. **Clone the repository** or create a new Java project and include the following dependencies in your `pom.xml`:
    ```xml
    <dependencies>
        <dependency>
            <groupId>net.sourceforge.tess4j</groupId>
            <artifactId>tess4j</artifactId>
            <version>4.5.4</version>
        </dependency>
        <dependency>
            <groupId>org.apache.httpcomponents</groupId>
            <artifactId>httpclient</artifactId>
            <version>4.5.13</version>
        </dependency>
    </dependencies>
    ```
2. **Configure Tesseract**:
   - Ensure Tesseract is installed and the executable is added to your PATH.
   - Set the `tessdata` directory path in the code: `tesseract.setDatapath("tessdata");`.

3. **Replace API Key**:
   - Sign up for FontMatcher API and get your API key.
   - Replace `YOUR_API_KEY` in the code with your actual API key.

## Running the Program

1. **Compile the Java program**:
   ```sh
   mvn compile

2. **Run the Java program**:
   ```sh
   mvn exec:java -Dexec.mainClass="FontIdentifier" -Dexec.args="/path/to/image/file.jpg"

  **Example**:
   ```css
   Identified Font: Arial


