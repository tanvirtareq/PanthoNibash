function downloadAsPdf() {
    const container = document.querySelector('#bookingContainer');
    html2canvas(container).then((canvas) => {
        const imgData = canvas.toDataURL('image/jpeg');
        const pdf = new jsPDF();
        const pdfWidth = 210;
        const pdfHeight = (canvas.height * pdfWidth) / canvas.width;

        pdf.addImage(imgData, 'JPEG', 0, 0, pdfWidth, pdfHeight);
        pdf.save('booking_details.pdf');
    });
}
