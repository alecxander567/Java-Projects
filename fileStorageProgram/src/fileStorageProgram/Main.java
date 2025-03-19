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
	

	public static void main(String[] args) {
		int option;
		
		// Your storage
		int fileStorage = 1000;
		int musicStorage = 1000;
		int videoStorage = 1000;
		
		System.out.println("FILE STORAGE PROGRAM");
		System.out.println("----------------------");
		
		// Values
		System.out.print("\nEnter how many files to save :");
		int fileSave = s.nextInt();
		System.out.print("Enter how many music to save : ");
		int musicSave = s.nextInt();
		System.out.print("Enter how many videos to save : ");
		int videoSave = s.nextInt();
		
		// Store the values
		String[] files = new String[fileSave];
		String[] musics = new String[musicSave];
		String[] videos= new String[videoSave];
		
		do {
			
			// Menu to choose what to do next
			System.out.println("\nMenu : ");
			System.out.println("[1] Save the files");
			System.out.println("[2] Save the musics");
			System.out.println("[3] Save the videos");
			System.out.println("[4] View files");
			System.out.println("[5] View musics");
			System.out.println("[6] Show videos");
			System.out.println("[7] Edit file name");
			System.out.println("[0] Exit");
			
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
	
				System.out.println("\nFiles save successfully!");
				System.out.println("Remaining storage : " + fileStorage);
				break;	
			case 2:
				for (int i = 0; i < musics.length; i++) {
					s.nextLine();
					
					// Name the musics
					System.out.print("Name the music :");
					String nameOfMusic = s.nextLine();
					musicStorage -= 50;
					
					// Store the music
					musics[i] = nameOfMusic;
				}
				
				System.out.println("\nMusics saved successfully!");
				System.out.println("Remaining storage : " + musicStorage);
				break;
			case 3:
				for (int i = 0; i < videos.length; i++) {
					s.nextLine();
					// Name the videos
					System.out.print("Name of the video : ");
					String nameOfVideo = s.nextLine();
					videoStorage -= 50;
					
					// Store the videos
					videos[i] = nameOfVideo;
				}
				
				System.out.println("\nVideos saved successfully!");
				System.out.println("Remaining storage : " + videoStorage);
				break;
				
			case 4:
				for (int i = 0; i < files.length; i++) {
					System.out.println("\nName of file : " + files[i]);
				}
				break;
				
			case 5:
				for (int i = 0; i < musics.length; i++) {
					System.out.println("\nName of music : " + musics[i]);
				}
				break;
			case 6:
				for (int i = 0; i < videos.length; i++) {
					System.out.println("\nName of video : " + videos[i]);
				}
				break;
			case 7:
				files = updatedFileName(files);
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
