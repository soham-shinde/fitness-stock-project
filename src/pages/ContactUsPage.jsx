import React, { useState } from 'react'
import NavBar from '../component/NavBar'
import Footer from '../component/Footer'
import { addContactUs } from '../api-client/api-module';
import AlertDialog from '../component/AlertDialog';
import Loader from '../component/Loader';

export default function ContactUsPage() {
    const [name, setName] = useState('');
    const [email, setEmail] = useState('');
    const [message, setMessage] = useState('');
    const [loading, setLoading] = useState(false);
    const [alert, setAlert] = useState(false);
    const [alertData, setAlertData] = useState({})

    const handleSubmit = async (e) => {
        e.preventDefault();
        setLoading(true);

        if (!name || !email || !message) {
            console.log('Please fill in all required fields.');
            return;
        }

        const formDate = {
            "name": name,
            "contactno": email,
            "msg": message
        }

        await addContactUs(formDate);
        setLoading(false);
        setAlertData({title:'Successfully',desc:'Your message successfully sent to admin',type:'ok'})
        setAlert(true)
        setName('');
        setEmail('');
        setMessage('');
    };

    return (
        <>
            <NavBar />
            <div className="body-container">
                <section className="contact-us-section">
                    <h2>Contact Us</h2>
                    <p>Feel free to reach out to us for any inquiries or feedback.</p>
                    <div className="contact-info">
                        <h3>Contact Information</h3>
                        <ul>
                            <li><strong>Email:</strong> info@example.com</li>
                            <li><strong>Phone:</strong> +1 (123) 456-7890</li>
                            <li><strong>Address:</strong> 123 Fitness Avenue, City, Country</li>
                        </ul>
                    </div>
                    <div className="contact-form">
                        <h3>Send Us a Message</h3>
                        <form onSubmit={handleSubmit}>
                            <label htmlFor="name">Name:</label>
                            <input type="text" id="name" value={name} onChange={(e) => setName(e.target.value)} required />
                            <label htmlFor="email">Email:</label>
                            <input type="email" id="email" required value={email} onChange={(e) => setEmail(e.target.value)} />
                            <label htmlFor="message">Message:</label>
                            <textarea id="message" rows="5" value={message} onChange={(e) => setMessage(e.target.value)} required></textarea>
                            <button type="submit" className="send-button">Send Message</button>
                        </form>
                    </div>
                </section>
            </div>
            <Footer />
            {loading && <Loader/>}
            {alert && <AlertDialog title={alertData.title} desc={alertData.desc} type={alertData.type} onClose={setAlert}/>}
        </>
    )
}
