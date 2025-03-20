package fileStorageProgram;
import java.util.Scanner;
public class Main {
	
	static Scanner s = new Scanner(System.in);
	
	public static String[] updatedFileName(String[] files) {
		boolean itemFound = false;
		int index = -1; // Set index to keep track of the loop
		
		String fileName;
		
		s.nextLine();
		System.out.print("\nEnter the file name to edit : ");
		fileName = s.nextLine();
		
		// Loop to search for the File name
		for (int i = 0; i < files.length; i++) {
			if (fileName.equalsIgnoreCase(files[i])) {
				itemFound = true;
				index = i;
			}			
		}
		
		// Update the file name
		System.out.print("Enter the new file name : ");
		String newFileName = s.nextLine();
		
		files[index] = newFileName;
		
		// If no such file
		if (!itemFound) {
			System.out.println("\nFile not found!");
			return files;
		}	
		
		System.out.println("\nFile name edited successfully!");
		return files;
	}
	
	
	public static String[] deleteFiles(String[] files) {
		boolean found = false;
		int index = -1; // Keep track of the loop
		
		String fileToDelete;
		
		s.nextLine();
		System.out.print("\nEnter the file name to delete : ");
		fileToDelete = s.nextLine();
		
		// Find the file to delete
		for (int i = 0; i < files.length; i++) {
			if (fileToDelete.equalsIgnoreCase(files[i])) {
				found = true;
				index = i;
			}
		}
		 
		// If not found
		if (!found) {
			System.out.println("\nFile not found!");
			return files;
		}
		
		// Create a new array to store the updated elements
		String[] updatedFiles = new String[files.length - 1];
		
		for (int i = 0, j = 0; i < files.length; i++) {
			if (i != index) {
				updatedFiles[j++] = files[i]; // Copies all the elements except for the specified index
			}
		}
		
		System.out.println("\nFile deleted successfully!");
		return updatedFiles;
	}
	
	
	public static String[] addedFiles(String[] files) {
		int addFiles;
		
		System.out.print("\nHow many files to add : ");
		addFiles = s.nextInt();
		
		// Make a new array
		String[] newFiles = new String[files.length + addFiles];
		
		// Copy all the elements from the original array
		for (int i = 0; i < files.length; i++) {
			newFiles[i] = files[i];
		}
		
		// Add the new elements
		for (int i = files.length; i < newFiles.length; i++) {
			s.nextLine();
			System.out.print("Enter the files name/s:");
			String newFileName = s.nextLine();
			
			// Store it in the new array
			newFiles[i] = newFileName;
		}
		
		System.out.println("\nFile/s added successfully!");
		return newFiles;
	}
	

	public static void main(String[] args) {
		int option;
		
		// Your storage
		int fileStorage = 1000;
		
		System.out.println("FILE STORAGE PROGRAM");
		System.out.println("----------------------");
		
		// Values
		System.out.print("\nEnter how many files to save :");
		int fileSave = s.nextInt();
		
		// Store the values
		String[] files = new String[fileSave];
		
		do {
			
			// Menu to choose what to do next
			System.out.println("\nMenu : ");
			System.out.println("[1] Save the files");
			System.out.println("[2] View files");
			System.out.println("[3] Edit files");
			System.out.println("[4] Delete files");
			System.out.println("[5] Add new file/s");
			System.out.println("[0] Exit the program...");
			
			System.out.print("\nEnter your choice : ");
			option = s.nextInt();
			
			switch (option) {
			case 1:
				for (int i = 0; i < files.length; i++) {
					s.nextLine();
					
					// Name the files
					System.out.print("Name of the file/s: ");
					String nameOfFile = s.nextLine();
					fileStorage -= 50;
							
					// Store the files
					files[i] = nameOfFile;
					
				}
				
				System.out.println("\nFiles sotred successfully!");
				System.out.println("Remaining storage : " + fileStorage);
				break;
			case 2:
				for (int i = 0; i < files.length; i++) {
					System.out.println("\nName of file : " + files[i]);
				}
				break;
			case 3:
				files = updatedFileName(files);
				break;
			case 4:
				files = deleteFiles(files);
				break;
			case 5:
				files = addedFiles(files);
				break;
			case 0:
				System.out.println("\nProgram terminated!...");
				break;		
			default:
				System.out.println("\nInvalid choice!");
				continue;
			}
					
		} while (option != 0);

	}

}
