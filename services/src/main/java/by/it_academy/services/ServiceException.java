package by.it_academy.services;

    public class ServiceException extends RuntimeException {
        public ServiceException(String message) {
            super(message);
        }

        public ServiceException(String message, Throwable cause) {
            super(message, cause);
        }
    }