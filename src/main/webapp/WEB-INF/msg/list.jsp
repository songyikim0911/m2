<%@include file="../includes/header.jsp"%>
<!-- Page Content-->
<div class="container-fluid p-0">
    <!-- About-->
    <section class="resume-section" id="about">
        <div class="resume-section-content">
            <h1 class="mb-0">
                Clarence ${Receive}
                <span class="text-primary">Taylor</span>
            </h1>
            <div class="subheading mb-5">
                ${Send}
                <a href="mailto:name@email.com">name@email.com</a>
            </div>
            <p class="lead mb-5">I am experienced in leveraging agile frameworks to provide a robust synopsis for high level overviews. Iterative approaches to corporate strategy foster collaborative thinking to further the overall value proposition.</p>
            <div class="social-icons">
                <a class="social-icon" href="#!"><i class="fab fa-linkedin-in"></i></a>
                <a class="social-icon" href="#!"><i class="fab fa-github"></i></a>
                <a class="social-icon" href="#!"><i class="fab fa-twitter"></i></a>
                <a class="social-icon" href="#!"><i class="fab fa-facebook-f"></i></a>
            </div>
        </div>
    </section>
    <hr class="m-0" />
    <!-- Experience-->
    <section class="resume-section" id="Receive">
        <div class="resume-section-content">
            <h2 class="mb-5">받는 메시지</h2>
            <c:forEach items="${Receive}" var="dto">
            <div class="d-flex flex-column flex-md-row justify-content-between mb-5">
                <div class="flex-grow-1">
                    <h3 class="mb-0"><a href="/msg/read?mno=${dto.mno}">${dto.mno}</a> - ${dto.who}</h3>
                    <div class="subheading mb-3">Intelitec Solutions</div>
                    <p>${dto.content}</p>
                </div>
                <div class="flex-shrink-0"><span class="text-primary">${dto.regdate}</span></div>
            </div>
            </c:forEach>

    </section>
    <!-- Experience-->
    <section class="resume-section" id="experience">
        <div class="resume-section-content">
            <h2 class="mb-5">보낸 메시지</h2>
            <c:forEach items="${Send}" var="dto">
            <div class="d-flex flex-column flex-md-row justify-content-between mb-5">
                <div class="flex-grow-1">
                    <h3 class="mb-0">${dto.mno} - ${dto.who}</h3>
                    <div class="subheading mb-3">Intelitec Solutions</div>
                    <p>${dto.content}</p>
                </div>
                <div class="flex-shrink-0"><span class="text-primary">${dto.regdate}</span></div>
            </div>
            </c:forEach>

    </section>

</div>
<!-- Bootstrap core JS-->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.bundle.min.js"></script>
<!-- Core theme JS-->
<script src="/js/scripts.js"></script>

<%@include file="../includes/footer.jsp"%>
