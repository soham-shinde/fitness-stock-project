
export const validateEmail = (email) => {
    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    return emailRegex.test(email);
};

export const validatePassword = (password) => {
    return password.length >= 6;
};
export const validateContact = (contact) => {
    return contact.length=== 10;
};

export function validateName(name) {
    return (/^[A-Z a-z]+$/.test(name) || name === '')
}

export function validateNumber(params) {
    return (/^\d*$/.test(params))
}

export function formatDate(inputDate) {
    const parts = inputDate.split('-');
    if (parts.length === 3) {
      const [year, month, day] = parts;
      return `${day}-${month}-${year}`;
    }
    return inputDate;
  }

/*
https://www.amazon.com/Signature-Fitness-Multifunctional-Extension-Preacher/dp/B0CBL99M94/ref=sr_1_9?keywords=gym+equipment&sr=8-9
https://www.amazon.com/Exercise-Bike-Stationary-Cycling-Comfortable-Equipment/dp/B0CDR9GM7T/ref=sr_1_51?keywords=gym+equipment&sr=8-51
https://www.amazon.com/FLYBIRD-Equipment-Adjustable-Strength-Training/dp/B0BDK4D36H/ref=sr_1_26?keywords=gym+equipment&sr=8-26
https://www.amazon.com/Abdominal-Multifunctional-Equipment-Resistance-Adjustable/dp/B092V7N9ZX/ref=sr_1_50_sspa?keywords=gym+equipment&sr=8-50-spons&sp_csd=d2lkZ2V0TmFtZT1zcF9hdGZfbmV4dA&psc=1
*/