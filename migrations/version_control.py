from datetime import datetime

def generate_migration_version(file):
    if ".sql" in file:
        dt = datetime.now().strftime('%Y%m%d%H%M%S%f')
        versioned_file = 'V{}__{}'.format(dt, file)
        return versioned_file
    elif file.startswith('V'):
        return "file is already versioned"
    else:
        return "Migartion file should have .sql extention"
if __name__ == "__main__":
    file_name = raw_input("Please provide sql file name : ")
    print generate_migration_version(file_name)