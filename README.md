# Watch

A Java project for reading files from a predefined directory and generating data consumption reports.

## Overview

This project monitors a specific folder for incoming files, processes their contents, and produces structured reports based on the consumed data. It's designed as a foundation for file-watching/reporting systems and can be extended for custom data processing needs.

## Features

- Monitors a designated directory for new files.
- Parses and processes input files automatically.
- Generates detailed reports from consumed data.
- Easily extensible for custom business logic.

## Getting Started

### Prerequisites

- Java 8 or higher
- Maven (for building and dependency management)

### Installation

1. **Clone the repository:**
   ```bash
   git clone https://github.com/YuriMostardeiro/watch.git
   cd watch
   ```

2. **Build the project:**
   ```bash
   mvn clean package
   ```

3. **Run the application:**
   ```bash
   java -jar target/watch-*.jar
   ```

### Configuration

By default, the application watches a specific folder for input files. You can configure the input and output directories in the source code or via environment variables (if implemented).

## Usage

Place your data files into the monitored directory. The application will automatically process them and output reports to the configured location.

Example:
- Drop `data.txt` into `/input`
- Find your processed report in `/output`

## Extending

To add custom file formats or processing logic, fork the repository and update the relevant Java classes responsible for file parsing and report generation.

## Contributing

Pull requests are welcome! For major changes, please open an issue first to discuss what you would like to change.

## License

This project is open source. Please add your preferred license (e.g., MIT, Apache 2.0) if you intend to share or reuse this code.

## Author

[Yuri Mostardeiro](https://github.com/YuriMostardeiro)

---
> _This README follows good practice templates and can be adapted as your project evolves._
