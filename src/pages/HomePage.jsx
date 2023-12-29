import React from 'react'
import "../css/style.css"
import MCart from '../component/MCart'
import NavBar from '../component/NavBar'
import Footer from '../component/Footer'
export default function HomePage() {
  return (
    <>
    <NavBar/>
     <div className="body-container">
        <div className="banner" style={{backgroundImage : `url(${process.env.PUBLIC_URL}/img/victor-freitas-WvDYdXDzkhs-unsplash.jpg)` }}>
            <div className="banner-content">
                <div className="tag-line">
                    <span>Get Healthy Body
                    </span>With the Perfect Exercises
                </div>

                <div className="explore-button">
                    Explore More
                </div>
            </div>
        </div>
        <div className="container-1">
            <div className="text-line">
                <h1>
                    Get Ready To Reach
                    Your Fitness Goals
                </h1>
                <p>
                    We are a gym that is committed to helping people reach their fitness goals. we offer a variety of
                    theirs programs and services to fit your needs, whether
                    you are a experienced athlete. We believe that everyone Should have access to the benefits of
                    exercise make it happen. Lorem ipsum dolor sit amet, consectetur adipisicing elit. Corrupti,
                    recusandae ullam. Minima sunt nemo molestiae nulla blanditiis, odit beatae quidem perferendis ullam
                    odio est reiciendis modi deserunt sit hic. Impedit.
                </p>
            </div>
            <div className="img-container">
                <img src="img/patrick-malleret-p-v1DBkTrgo-unsplash.jpg" alt=""/>
            </div>
        </div>
        <div className="container-2">
            <div className="con-heading">
                <h2>The Best Programs </h2>
                <p>We Offers For You</p>
            </div>
            <div className="cart-container">
                <div className="p-carts">
                    <img src="img/icon-1.svg" alt=""/>
                    <h3>Body Building
                    </h3>
                    <p>
                        For those making to increase strength build lean muscle, our
                        Strength & muscle.</p>
                </div>
                <div className="p-carts">
                    <img src="img/icon-2.svg" alt=""/>
                    <h3>Weight Loss</h3>
                    <p>our weight loss programs are designed to help you make sustainable lifestyle changes</p>
                </div>
                <div className="p-carts">
                    <img src="img/icon-3.svg" alt=""/>
                    <h3>Strength Training</h3>
                    <p>Our trainers will design thot a progressive workout plans that proper achieve gains strength.</p>
                </div>
                <div className="p-carts">
                    <img src="img/icon-4.svg" alt=""/>
                    <h3>Basic Yoga
                    </h3>
                    <p>This combines with cardio & Strength training to help lose weight & fitness.</p>
                </div>
            </div>
        </div>
        
        <div className="info-container">
            <div className="footer-box" id="about">
                <h2>About Us</h2>
                <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed a metus euismod, interdum velit in,
                    scelerisque ligula. Ut in tellus ac enim aliquam laoreet eu at eros. Donec euismod dignissim orci id
                    vestibulum.</p>
            </div>
            <div className="footer-box contact-box" id="contact">
                <h2>Contact Us</h2>
                <div className="contact-details">
                    <table>
                        <tr>
                            <td className="strong">Phone:</td>
                            <td className="td-raw"> +1 123-456-7890</td>
                        </tr>
                        <tr>
                            <td className="strong">Email:</td>
                            <td className="td-raw">  example@example.com</td>
                        </tr>
                        <tr>
                            <td className="strong">Location:</td>
                            <td className="td-raw"> 1234 Elm Street, City, Country</td>
                        </tr>
                        <tr>
                            <td className="strong">Hours:</td>
                            <td className="td-raw"> Monday to Saturday <br/> 9:00 AM to 6:00 PM</td>
                        </tr>
                    </table>
                </div>
            </div>
        </div>
    </div>
    <Footer/>
    </>
  )
}
