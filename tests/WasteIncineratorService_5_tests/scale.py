import time

def main():
    num = 51
    time.sleep(3)
    num += 1
    print(num, flush=True)
    
    try:
        while True:
            print(num, flush=True)
            num += 1
            time.sleep(10)
    except KeyboardInterrupt:
        print("\nError")

if __name__ == "__main__":
    main()