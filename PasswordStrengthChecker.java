import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.ArrayList;
import java.util.List;

public class PasswordStrengthChecker {
    
    // Regular expression patterns for password validation
    private static final Pattern UPPERCASE_PATTERN = Pattern.compile("[A-Z]");
    private static final Pattern LOWERCASE_PATTERN = Pattern.compile("[a-z]");
    private static final Pattern DIGIT_PATTERN = Pattern.compile("[0-9]");
    private static final Pattern SPECIAL_CHAR_PATTERN = Pattern.compile("[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?]");
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("╔════════════════════════════════════════╗");
        System.out.println("║   PASSWORD STRENGTH CHECKER            ║");
        System.out.println("╚════════════════════════════════════════╝");
        System.out.println();
        
        while (true) {
            System.out.print("Enter password to check (or 'exit' to quit): ");
            String password = scanner.nextLine();
            
            if (password.equalsIgnoreCase("exit")) {
                System.out.println("\nThank you for using Password Strength Checker!");
                break;
            }
            
            if (password.isEmpty()) {
                System.out.println("❌ Password cannot be empty!\n");
                continue;
            }
            
            // Analyze password
            PasswordAnalysis analysis = analyzePassword(password);
            
            // Display results
            displayResults(analysis);
            System.out.println();
        }
        
        scanner.close();
    }
    
    /**
     * Analyzes password strength and returns detailed analysis
     */
    public static PasswordAnalysis analyzePassword(String password) {
        PasswordAnalysis analysis = new PasswordAnalysis();
        analysis.password = password;
        analysis.length = password.length();
        
        // Check for character types
        analysis.hasUppercase = UPPERCASE_PATTERN.matcher(password).find();
        analysis.hasLowercase = LOWERCASE_PATTERN.matcher(password).find();
        analysis.hasDigit = DIGIT_PATTERN.matcher(password).find();
        analysis.hasSpecialChar = SPECIAL_CHAR_PATTERN.matcher(password).find();
        
        // Check for common weak patterns
        analysis.hasSequentialChars = hasSequentialCharacters(password);
        analysis.hasRepeatingChars = hasRepeatingCharacters(password);
        
        // Calculate strength score
        analysis.score = calculateScore(analysis);
        
        // Determine strength level
        if (analysis.score >= 80) {
            analysis.strength = "Strong";
            analysis.color = "🟢";
        } else if (analysis.score >= 50) {
            analysis.strength = "Medium";
            analysis.color = "🟡";
        } else {
            analysis.strength = "Weak";
            analysis.color = "🔴";
        }
        
        // Generate suggestions
        analysis.suggestions = generateSuggestions(analysis);
        
        return analysis;
    }
    
    /**
     * Calculates password strength score (0-100)
     */
    private static int calculateScore(PasswordAnalysis analysis) {
        int score = 0;
        
        // Length scoring (max 30 points)
        if (analysis.length >= 12) {
            score += 30;
        } else if (analysis.length >= 8) {
            score += 20;
        } else if (analysis.length >= 6) {
            score += 10;
        }
        
        // Character variety scoring (max 40 points)
        if (analysis.hasUppercase) score += 10;
        if (analysis.hasLowercase) score += 10;
        if (analysis.hasDigit) score += 10;
        if (analysis.hasSpecialChar) score += 10;
        
        // Bonus points for length beyond 12 (max 20 points)
        if (analysis.length > 12) {
            score += Math.min(20, (analysis.length - 12) * 2);
        }
        
        // Penalties for weak patterns
        if (analysis.hasSequentialChars) score -= 15;
        if (analysis.hasRepeatingChars) score -= 10;
        
        // Ensure score is within bounds
        return Math.max(0, Math.min(100, score));
    }
    
    /**
     * Checks for sequential characters (abc, 123, etc.)
     */
    private static boolean hasSequentialCharacters(String password) {
        String lowerPassword = password.toLowerCase();
        for (int i = 0; i < lowerPassword.length() - 2; i++) {
            char c1 = lowerPassword.charAt(i);
            char c2 = lowerPassword.charAt(i + 1);
            char c3 = lowerPassword.charAt(i + 2);
            
            if (c2 == c1 + 1 && c3 == c2 + 1) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Checks for repeating characters (aaa, 111, etc.)
     */
    private static boolean hasRepeatingCharacters(String password) {
        for (int i = 0; i < password.length() - 2; i++) {
            if (password.charAt(i) == password.charAt(i + 1) && 
                password.charAt(i) == password.charAt(i + 2)) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Generates improvement suggestions based on analysis
     */
    private static List<String> generateSuggestions(PasswordAnalysis analysis) {
        List<String> suggestions = new ArrayList<>();
        
        if (analysis.length < 8) {
            suggestions.add("Increase length to at least 8 characters (currently: " + analysis.length + ")");
        } else if (analysis.length < 12) {
            suggestions.add("Consider using 12+ characters for better security");
        }
        
        if (!analysis.hasUppercase) {
            suggestions.add("Add uppercase letters (A-Z)");
        }
        
        if (!analysis.hasLowercase) {
            suggestions.add("Add lowercase letters (a-z)");
        }
        
        if (!analysis.hasDigit) {
            suggestions.add("Add numbers (0-9)");
        }
        
        if (!analysis.hasSpecialChar) {
            suggestions.add("Add special characters (!@#$%^&*...)");
        }
        
        if (analysis.hasSequentialChars) {
            suggestions.add("Avoid sequential characters (abc, 123, etc.)");
        }
        
        if (analysis.hasRepeatingChars) {
            suggestions.add("Avoid repeating characters (aaa, 111, etc.)");
        }
        
        if (suggestions.isEmpty()) {
            suggestions.add("Excellent! Your password is strong.");
        }
        
        return suggestions;
    }
    
    /**
     * Displays formatted analysis results
     */
    private static void displayResults(PasswordAnalysis analysis) {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("PASSWORD ANALYSIS RESULTS");
        System.out.println("=".repeat(50));
        
        // Strength rating with visual indicator
        System.out.printf("%s Strength: %s (%d/100)%n", 
            analysis.color, analysis.strength, analysis.score);
        
        // Visual strength bar
        System.out.print("Strength Bar: [");
        int filledBars = analysis.score / 5;
        for (int i = 0; i < 20; i++) {
            if (i < filledBars) {
                System.out.print("█");
            } else {
                System.out.print("░");
            }
        }
        System.out.println("]");
        
        System.out.println("\n" + "-".repeat(50));
        System.out.println("DETAILS:");
        System.out.println("-".repeat(50));
        
        // Character analysis
        System.out.printf("Length: %d characters %s%n", 
            analysis.length, 
            analysis.length >= 12 ? "✓" : (analysis.length >= 8 ? "⚠" : "✗"));
        
        System.out.printf("Uppercase letters: %s%n", 
            analysis.hasUppercase ? "✓ Yes" : "✗ No");
        
        System.out.printf("Lowercase letters: %s%n", 
            analysis.hasLowercase ? "✓ Yes" : "✗ No");
        
        System.out.printf("Numbers: %s%n", 
            analysis.hasDigit ? "✓ Yes" : "✗ No");
        
        System.out.printf("Special characters: %s%n", 
            analysis.hasSpecialChar ? "✓ Yes" : "✗ No");
        
        // Warning flags
        if (analysis.hasSequentialChars || analysis.hasRepeatingChars) {
            System.out.println("\n⚠️  WARNING FLAGS:");
            if (analysis.hasSequentialChars) {
                System.out.println("  • Contains sequential characters");
            }
            if (analysis.hasRepeatingChars) {
                System.out.println("  • Contains repeating characters");
            }
        }
        
        // Suggestions
        System.out.println("\n" + "-".repeat(50));
        System.out.println("SUGGESTIONS FOR IMPROVEMENT:");
        System.out.println("-".repeat(50));
        for (int i = 0; i < analysis.suggestions.size(); i++) {
            System.out.printf("%d. %s%n", i + 1, analysis.suggestions.get(i));
        }
    }
}

/**
 * Data class to hold password analysis results
 */
class PasswordAnalysis {
    String password;
    int length;
    boolean hasUppercase;
    boolean hasLowercase;
    boolean hasDigit;
    boolean hasSpecialChar;
    boolean hasSequentialChars;
    boolean hasRepeatingChars;
    int score;
    String strength;
    String color;
    List<String> suggestions;
}
