import { useEffect, useState } from "react";
import NavBar from "../component/NavBar";
import Footer from "../component/Footer";


function About() {
  return(
    <>
      <NavBar/>
      <div className="body-container">
      <section className="about-us-section">
    <h2>About Us</h2>
    <p>Welcome to our Gym Management System! We provide a state-of-the-art platform to manage your fitness journey.</p>
    <p>Our system offers a variety of features for customers, trainers, and staff members. From managing memberships and equipment to assigning tasks and tracking progress, our system is designed to enhance your fitness experience.</p>
    <div className="photos-section">
        <h3>Photos</h3>
        <div className="photos">
            <img src="/img/patrick-malleret-p-v1DBkTrgo-unsplash.jpg" alt="Photo 1"/>
            <img src="/img/bruce-mars-gJtDg6WfMlQ-unsplash.jpg" alt="Photo 2"/>
            <img src="/img/cagin-kargi-Qzp60FT380E-unsplash.jpg" alt="Photo 3"/>
        </div>
    </div>
    <div className="owners-section">
        <h3>Meet Our Team</h3>
        <div className="owner-cards">
            <div className="owner-card">
                <img src="/img/profile.jpg" alt="Owner 1"/>
                <h4>Soham Shinde</h4>
                <p>Developer</p>
            </div>
            <div className="owner-card">
                <img src="/img/profile.jpg" alt="Owner 1"/>
                <h4>Om Vastre</h4>
                <p>Developer</p>
            </div>
         
           
        </div>
    </div>
</section></div>
      <Footer/>
    </>
  )
  }

export default About;