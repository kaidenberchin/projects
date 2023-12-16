import os

def change_tag_to_null(folder_path):
    for file in os.listdir(folder_path):
        #checks if file is a txt file
        if file.endswith(".txt"):
            #constructs the full file path
            file_path = os.path.join(folder_path, file)
            #opens the file for reading and reads all lines
            with open(file_path, 'r') as file:
                lines = file.readlines()
            
            #opens the file for writing and checks if any line in txt file starts with Nation
            with open(file_path, 'w') as file:
                for line in lines:
                    if line.startswith("Nation:"):
                        file.write("Nation: NUL\n")
                    else:
                        file.write(line)

# Replace 'your_folder_path' with the path to the folder you want to process
folder_path = r'C:\Users\nerdb\Desktop\Test file'
# Call the function with the specified folder path
change_tag_to_null(folder_path)