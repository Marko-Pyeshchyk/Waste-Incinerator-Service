import time

def main():
    num = 49
    time.sleep(3)
    num += 1
    print(num, flush=True)
    
    try:
        while True:
            print(num, flush=True)
            num += 1
            time.sleep(8)
    except KeyboardInterrupt:
        print("\nError")

if __name__ == "__main__":
    main()