# password-strength-checker
Java application for checking password strength
# Password Strength Checker �

A Java application that analyzes password strength and provides detailed improvement suggestions.

##  Features

- **Password Strength Rating**: Weak / Medium / Strong classification
- **Comprehensive Analysis**: Checks for uppercase, lowercase, numbers, and special characters
- **Pattern Detection**: Identifies sequential (abc, 123) and repeating characters (aaa, 111)
- **Visual Feedback**: Score from 0-100 with progress bar
- **Smart Suggestions**: Personalized recommendations for improvement
- **Interactive CLI**: User-friendly command-line interface

##  Requirements

- Java 8 or higher
- JDK installed on your system

##  How to Run

### Step 1: Download
Download `PasswordStrengthChecker.java` from this repository

### Step 2: Compile
```bash
javac PasswordStrengthChecker.java
```

### Step 3: Execute
```bash
java PasswordStrengthChecker
```

##  Usage

1. Run the program
2. Enter a password when prompted
3. View detailed strength analysis
4. Review improvement suggestions
5. Type 'exit' to quit

## 📸 Example Output

```
╔════════════════════════════════════════╗
║   PASSWORD STRENGTH CHECKER            ║
╚════════════════════════════════════════╝

Enter password to check: MyP@ssw0rd123

==================================================
PASSWORD ANALYSIS RESULTS
==================================================
 Strength: Strong (85/100)
Strength Bar: [█████████████████░░░]

--------------------------------------------------
DETAILS:
--------------------------------------------------
Length: 13 characters ✓
Uppercase letters: ✓ Yes
Lowercase letters: ✓ Yes
Numbers: ✓ Yes
Special characters: ✓ Yes

--------------------------------------------------
SUGGESTIONS FOR IMPROVEMENT:
--------------------------------------------------
1. Excellent! Your password is strong.
```

##  Password Strength Criteria

###  Strong (80-100 points)
- 12+ characters
- Mix of uppercase and lowercase letters
- Contains numbers
- Contains special characters
- No sequential or repeating patterns

###  Medium (50-79 points)
- 8-11 characters
- Missing 1-2 character types
- May have minor pattern issues

###  Weak (0-49 points)
- Less than 8 characters
- Missing multiple character types
- Contains obvious patterns

##  Technical Details

**Language**: Java

**Key Concepts**:
- Regular expressions (regex) for pattern matching
- String manipulation
- Object-oriented programming
- Input validation and error handling
- ArrayList for dynamic data storage

**Classes**:
- `PasswordStrengthChecker`: Main program logic and UI
- `PasswordAnalysis`: Data class for analysis results

##  What I Learned

- Advanced string manipulation in Java
- Using regex patterns for validation
- Implementing scoring algorithms
- Creating intuitive command-line interfaces
- Structuring code with OOP principles

## Author

**Jaisurya R**

##  License

This project is open source and available under the MIT License.

##  Contributing

Contributions, issues, and feature requests are welcome!

##  Show Your Support

Give a  if this project helped you!

---

**Built with using Java**
